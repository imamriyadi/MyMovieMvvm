package tech.dreamcircle.mymoviemvvm.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor (private val movieApi: MovieApi){

}