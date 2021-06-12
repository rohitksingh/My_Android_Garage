## Dagger 2 Tutorials

These step is used all the time

- Created an BaseApplication   (define name tag in Manifest.xml file too)

      public class BaseApplication extends DaggerApplication {
      
        @Override
        protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

          return DaggerAppComponent.builder().application(this).build();

         }
      }
      
- Create AppComponent

        @Component( modules = { AndroidSupportInjectionModule.class )
        public interface AppComponent extends AndroidInjector<BaseApplication> {

            @Component.Builder
            interface Builder{

                @BindsInstance
                Builder application(Application application);

                AppComponent build();

            }

        }



