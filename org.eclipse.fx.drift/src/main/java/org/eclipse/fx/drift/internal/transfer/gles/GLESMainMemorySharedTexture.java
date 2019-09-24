package org.eclipse.fx.drift.internal.transfer.gles;

import org.eclipse.fx.drift.internal.GraphicsPipelineUtil;
import org.eclipse.fx.drift.internal.SYS;
import org.eclipse.fx.drift.internal.math.Vec2i;
import org.eclipse.fx.drift.internal.transfer.SharedTexture;

import com.sun.prism.Texture;

import static org.eclipse.fx.drift.internal.GL.*;
import static org.eclipse.fx.drift.internal.SYS.*;

public class GLESMainMemorySharedTexture extends SharedTexture {

	
	private Vec2i size;
	
	// alloc state
	int glTexture;
	long memPointer;
	int memSize;
	
	
	private long pointer;
	
	
	
	public GLESMainMemorySharedTexture(Vec2i size) {
		this.size = size;
		this.pointer = nCreate();
	}

	
	@Override
	protected void allocate() {
		glTexture = glGenTexture();
		glBindTexture(GL_TEXTURE_2D, glTexture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, size.getX(), size.getY(), 0, GL_BGRA, GL_UNSIGNED_BYTE, 0);
		glBindTexture(GL_TEXTURE_2D, 0);
		
		memSize = size.getX() * size.getY() * 4;
		memPointer = malloc(memSize);
	}
	
	@Override
	protected void release() {
		glDeleteTexture(glTexture);
		free(memPointer);
	}
	
	@Override
	protected void onAcquire() {
		
	}
	
	@Override
	protected void onPresent() {
		// download to memory
		downloadToMemoryBuf(glTexture, memSize, memPointer);
		
	}
	
	private void downloadToMemorySimple(int tex, long pPixels) {
		int format = GL_RGBA; // TODO need GL_BGRA on windows
		glBindTexture(GL_TEXTURE_2D, glTexture);
		glGetTexImage(GL_TEXTURE_2D, 0, format, GL_UNSIGNED_INT_8_8_8_8_REV, memPointer);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private void downloadToMemoryBuf(int tex, int size, long pPixels) {
		int buf = glGenBuffer();
		glBindBuffer(GL_PIXEL_PACK_BUFFER, buf);
		glBufferData(GL_PIXEL_PACK_BUFFER, size, 0, GL_STATIC_READ);
		int format = GL_RGBA; // TODO need GL_BGRA on windows
		glBindTexture(GL_TEXTURE_2D, tex);
		glGetTexImage(GL_TEXTURE_2D, 0, format, GL_UNSIGNED_INT_8_8_8_8_REV, 0);
		glBindTexture(GL_TEXTURE_2D, 0);
		
		long glBuf = glMapBuffer(GL_PIXEL_PACK_BUFFER, GL_READ_ONLY);
		memcpy(pPixels, glBuf, size);
		glUnmapBuffer(GL_PIXEL_PACK_BUFFER);
		glBindBuffer(GL_PIXEL_PACK_BUFFER, 0);
		glDeleteBuffer(buf);
	}
	
	@Override
	protected void OnTextureCreated(Texture texture) {
		int targetTex = GraphicsPipelineUtil.ES2.getTextureName(texture);
		uploadTexture(targetTex, width, height, memPointer, memSize);
	}
	
	private void uploadTexture(int targetTex, int width, int height, long pPixels, int size) {
		glBindTexture(GL_TEXTURE_2D, targetTex);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV, pPixels);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	
	private native long nCreate();
	private native void nDispose(long pointer);
	
}
