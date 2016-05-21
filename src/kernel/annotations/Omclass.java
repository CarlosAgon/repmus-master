package kernel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE })
public @interface Omclass {

	String icon () default "255";
	String editorClass () default "kernel.frames.views.EditorView";
	int boxsizex () default 300;
	int boxsizey () default  50;

}
