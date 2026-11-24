
package com.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.courier.entity.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

}
