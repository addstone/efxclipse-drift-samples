package org.eclipse.fx.drift.samples.cpp;

import org.eclipse.fx.drift.samples.IDriftFXSampleProvider;
import org.osgi.service.component.annotations.Component;

import javafx.scene.Node;

@Component(xmlns = "http://www.osgi.org/xmlns/scr/v1.3.0")
public class SimpleColorSampleProvider implements IDriftFXSampleProvider {
	
	@Override
	public String getTitle() {
		return "cpp - simple color";
	}
	
	@Override
	public Node createInstance() {
		return new SimpleColorSample();
	}

}
