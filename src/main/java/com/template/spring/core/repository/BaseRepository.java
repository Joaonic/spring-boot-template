package com.template.spring.core.repository;

import com.template.spring.core.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    //Override CrudRepository or PagingAndSortingRepository's query method:
    @Override
    @Query("select e from #{#entityName} e where e.deletedAt is null")
    List<T> findAll();

    //Look up deleted entities
    @Query("select e from #{#entityName} e where e.deletedAt is not null")
    List<T> recycleBin();

    //Soft delete.
    @Query("update #{#entityName} e set e.deletedAt=CURRENT_TIMESTAMP where e.id=?1")
    @Modifying
    void softDelete(String id);

}
