package app.suprsend.android

import kotlinx.coroutines.flow.Flow

class UserRepository(
    val userDao: UserDao,
    val userNetwork: UserNetwork
) {

    suspend fun insertUser(userModel: UserModel) {
        userDao.insert(userModel.id, userModel.name, userModel.company!!)
    }

    suspend fun getUsers(): Flow<List<UserModel>> {
        return userDao.select()
    }

    suspend fun makeRemoteCall(): String? {
        return userNetwork.getUsers()
    }

    companion object {
        fun getInstance(): UserRepository {
            val userDao = UserDao(globalDatabase.get()!!.suprSendDatabase)
            val userNetwork = UserNetwork()
            return UserRepository(userDao,userNetwork)
        }
    }
}