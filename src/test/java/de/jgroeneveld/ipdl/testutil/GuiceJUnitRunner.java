package de.jgroeneveld.ipdl.testutil;

/**
 * Created by jgroeneveld on 05.12.14.
 * source: http://fabiostrozzi.eu/2011/03/27/junit-tests-easy-guice/
 */
public class GuiceJUnitRunner { // extends BlockJUnit4ClassRunner {
//    private Injector injector;
//
//    @Target(ElementType.TYPE)
//    @Retention(RetentionPolicy.RUNTIME)
//    @Inherited
//    public @interface GuiceModules {
//        Class<?>[] value();
//    }
//
//    @Override
//    public Object createTest() throws Exception {
//        Object obj = super.createTest();
//        injector.injectMembers(obj);
//        return obj;
//    }
//
//    public GuiceJUnitRunner(Class<?> klass) throws InitializationError {
//        super(klass);
//        Class<?>[] classes = getModulesFor(klass);
//        injector = createInjectorFor(classes);
//    }
//
//    private Injector createInjectorFor(Class<?>[] classes) throws InitializationError {
//        Module[] modules = new Module[classes.length];
//        for (int i = 0; i < classes.length; i++) {
//            try {
//                modules[i] = (Module) (classes[i]).newInstance();
//            } catch (InstantiationException | IllegalAccessException e) {
//                throw new InitializationError(e);
//            }
//        }
//        return Guice.createInjector(modules);
//    }
//
//    private Class<?>[] getModulesFor(Class<?> klass) throws InitializationError {
//        GuiceModules annotation = klass.getAnnotation(GuiceModules.class);
//        if (annotation == null)
//            throw new InitializationError(
//                    "Missing @GuiceModules annotation for unit test '" + klass.getName()
//                            + "'");
//        return annotation.value();
//    }
}
