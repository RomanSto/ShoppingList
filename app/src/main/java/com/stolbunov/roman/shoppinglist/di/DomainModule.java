package com.stolbunov.roman.shoppinglist.di;

import com.stolbunov.roman.data.UserShoppingRepositoryManager;
import com.stolbunov.roman.domain.repository.IAuthorizationRepository;
import com.stolbunov.roman.domain.repository.IShopServiceRepository;
import com.stolbunov.roman.domain.repository.IUserShoppingRepository;
import com.stolbunov.roman.domain.use_cases.IAuthUseCase;
import com.stolbunov.roman.domain.use_cases.IShopServiceUseCase;
import com.stolbunov.roman.domain.use_cases.IUserShoppingUseCase;
import com.stolbunov.roman.domain.use_cases.ShoppingInteraptor;

import dagger.Binds;
import dagger.Module;

@Module
public interface DomainModule {
    @Binds
    IAuthUseCase provideAuthUseCase(ShoppingInteraptor interaptor);

    @Binds
    IShopServiceUseCase provideShopServiceUseCase(ShoppingInteraptor interaptor);

    @Binds
    IUserShoppingUseCase provideUserShoppingUseCase(ShoppingInteraptor interaptor);

    @Binds
    IAuthorizationRepository provideAuthorizationRepository(UserShoppingRepositoryManager manager);

    @Binds
    IUserShoppingRepository provideUserShoppingRepository(UserShoppingRepositoryManager manager);

    @Binds
    IShopServiceRepository provideShoppingRepository(UserShoppingRepositoryManager manager);
}
