package org.eclipse.fx.drift.internal.backend;

import org.eclipse.fx.drift.RenderTarget;
import org.eclipse.fx.drift.internal.common.ImageData;

public interface Image extends RenderTarget {

	public static class ImageType {
		public final String id;
		public ImageType(String id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return id;
		}
	}
	
	ImageData getData();
	
	void allocate();
	void release();
	void beforeRender();
	void afterRender();
	
	
	
	int getGLTexture();
	
}

