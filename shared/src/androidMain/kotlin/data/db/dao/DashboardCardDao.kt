package data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import data.db.entity.DashboardCardEntity
import io.reactivex.Flowable

/**
 * Created by Sonphil on 04-06-19.
 */

@Dao
interface DashboardCardDao {
    @Query("SELECT * FROM dashboardcardentity ORDER BY position")
    fun getAll(): Flowable<List<DashboardCardEntity>>

    @Update
    suspend fun updateDashboardCard(dashboardCardEntity: DashboardCardEntity)

    @Insert
    suspend fun insertDashboardCard(dashboardCardEntity: DashboardCardEntity)

    @Query("DELETE FROM dashboardcardentity")
    suspend fun deleteAll()
}