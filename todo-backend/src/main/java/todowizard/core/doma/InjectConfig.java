package todowizard.core.doma;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@AnnotateWith(annotations = { @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Inject.class) // ,
// @Annotation(target = AnnotationTarget.CONSTRUCTOR_PARAMETER, type =
// Named.class, elements = )
})
public @interface InjectConfig {

}
