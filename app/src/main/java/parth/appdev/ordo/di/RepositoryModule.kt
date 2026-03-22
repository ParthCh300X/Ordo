package parth.appdev.ordo.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import parth.appdev.ordo.data.remote.firebase.MenuService
import parth.appdev.ordo.data.remote.firebase.OrderService
import parth.appdev.ordo.data.repository.AuthRepositoryImpl
import parth.appdev.ordo.data.repository.MenuRepositoryImpl
import parth.appdev.ordo.data.repository.OrderRepositoryImpl
import parth.appdev.ordo.domain.repository.AuthRepository
import parth.appdev.ordo.domain.repository.MenuRepository
import parth.appdev.ordo.domain.repository.OrderRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideMenuService(
        firestore: FirebaseFirestore
    ): MenuService {
        return MenuService(firestore)
    }

    @Provides
    @Singleton
    fun provideMenuRepository(
        service: MenuService
    ): MenuRepository {
        return MenuRepositoryImpl(service)
    }
    @Provides
    @Singleton
    fun provideOrderService(
        firestore: FirebaseFirestore
    ): OrderService {
        return OrderService(firestore)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(
        service: OrderService
    ): OrderRepository {
        return OrderRepositoryImpl(service)
    }
}