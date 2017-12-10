package com.peterdang.offlineandroid.di;

import com.peterdang.offlineandroid.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by phuoc on 2017-12-10.
 */
@Singleton
@Component(modules = {
        /* Use AndroidInjectionModule.class if you're not using support library */
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);
        AppComponent build();
    }
    void inject(App app);
}
