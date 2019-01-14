package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Podanie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PodanieRepository extends JpaRepository<Podanie, Long> {

//UPDATE Customers
//SET ContactName='Juan'
//WHERE Country='Mexico';

    @Query("UPDATE Podanie SET stan='1' WHERE id=':stan1'"
    )
    void zmienstan1(@Param("stan1") Long stan1);

    @Query("UPDATE Podanie SET stan='2' WHERE id=':stan2'"
    )
    void zmienstan2(@Param("stan2") Long stan2);

    @Query("UPDATE Podanie SET stan='3' WHERE id=':stan3'"
    )
    void zmienstan3(@Param("stan3") Long stan3);

    @Query("UPDATE Podanie SET stan='4' WHERE id=':stan4'"
    )
    void zmienstan4(@Param("stan4") Long stan4);

}

