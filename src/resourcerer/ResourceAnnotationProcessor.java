package resourcerer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.Messager;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.FieldDeclaration;

public class ResourceAnnotationProcessor implements AnnotationProcessor {

	private final AnnotationProcessorEnvironment env;
	
	private volatile Properties props;

	public ResourceAnnotationProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}

	public void process() {
		Messager messager = env.getMessager();
		
		AnnotationTypeDeclaration annoDecl = (AnnotationTypeDeclaration)env.getTypeDeclaration(ResourceAnnotationProcessorFactory.RESOURCE_ANNOTATION);
		
		// get the annotated types
		Collection<Declaration> annotatedTypes = env.getDeclarationsAnnotatedWith(annoDecl);
		
		for (Declaration declaration : annotatedTypes) {
			if (declaration instanceof FieldDeclaration) {
				FieldDeclaration field = (FieldDeclaration)declaration;
				Properties props = getResourceProperties(field);
				if (props != null) {
					String resourceKey = field.getDeclaringType().getSimpleName() + "." + field.getSimpleName();
					String value = props.getProperty(resourceKey);
					if (value == null) {
						messager.printWarning(field.getPosition(), "No value defined for: " + resourceKey);
					} else {
						messager.printNotice(field.getPosition(), resourceKey + " = " + value);
					}
				}
			}
		}
	}
	
	private Properties getResourceProperties(Declaration declaration) {
		if (props != null) {
			return props;
		}
		String resourceFile = env.getOptions().get("resourceFile");
		Properties props = new Properties();
		if (resourceFile != null) {
			try {
				props.load(new FileInputStream(resourceFile));
				this.props = props;
				return props;
			} catch (IOException e) {
				env.getMessager().printWarning(declaration.getPosition(), "Could not read resource file: " + resourceFile);
			}
		} else {
			env.getMessager().printWarning(declaration.getPosition(), "No resourceFile defined in options");
		}
		return props;
	}

}
