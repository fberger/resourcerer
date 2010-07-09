package resourcerer;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class ResourceAnnotationProcessorFactory implements AnnotationProcessorFactory {

	public static final String RESOURCE_ANNOTATION = "org.jdesktop.application.Resource";
	
	public AnnotationProcessor getProcessorFor(
			Set<AnnotationTypeDeclaration> arg0,
			AnnotationProcessorEnvironment env) {
		return new ResourceAnnotationProcessor(env);
	}

	public Collection<String> supportedAnnotationTypes() {
		return Collections.singleton(RESOURCE_ANNOTATION);
	}

	public Collection<String> supportedOptions() {
		return Collections.singleton("-AresourceFile");
	}

}
