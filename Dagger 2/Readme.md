## Dagger 2 Tutorials

## Basic Setup - Creation of BaseApplication and AppComponent

These step is used all the time. I consider it as a basic setup boilerplate code!. You can literally copy paste this in your application.

- Created an BaseApplication   (Application Layer)

      public class BaseApplication extends DaggerApplication {
      
        @Override
        protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

          return DaggerAppComponent.builder().application(this).build();

         }
      }
      
- Create AppComponent. (Component)

        @Component( modules = { AndroidSupportInjectionModule.class )
        public interface AppComponent extends AndroidInjector<BaseApplication> {

            @Component.Builder
            interface Builder{

                @BindsInstance
                Builder application(Application application);

                AppComponent build();

            }

        }


#### Now we have created Application and AppComponent. 


## Create Modules

In this section we create Modules: ActivityBuilderModule + Other Modules   

#### ActivityBuilderModule

            @Module
            public abstract class ActivityBuilderModule {

                @ContributesAndroidInjector
                abstract AuthActivity contributeAuthActivity();

            }

#### AppModule - This module is containes dependency which are used at Application level ie created on time (Do not quote me on this!)

Note :  @Provides - Tells that this is a dependency in this module.      
        @Inject   - Used as dependency


            @Module
            public class AppModule {

                @Provides
                static String initialName(){
                    return "This is initial name of the String";
                }

                @Provides
                static RequestOptions provideRequestOption(){
                    return RequestOptions.placeholderOf(R.drawable.cat_logo);
                }

                @Provides
                static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
                    return Glide.with(application)
                            .setDefaultRequestOptions(requestOptions);
                }

                @Provides
                static Drawable provideAppDrawables(Application application){
                    return ContextCompat.getDrawable(application, R.drawable.cat_logo);
                }
            }
            
#### Activity part where the AppModule and the dependency is used

            public class AuthActivity extends DaggerAppCompatActivity {


                @Inject
                Drawable catLogo;

                @Inject
                RequestManager requestManager;

                private ImageView logoImageView;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_auth);
                    logoImageView = findViewById(R.id.logo);
                    loadLogo();
                }

                private void loadLogo(){
                    requestManager
                            .load(catLogo)
                            .into(logoImageView);
                }
            }



### Activity is the Consumer and AppModule is Provider





