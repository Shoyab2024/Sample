package com.donar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donar.entity.DonarDetails;
import java.util.List;


public interface DonarRepository extends JpaRepository<DonarDetails, Integer>
{
	DonarDetails findByEmailid(String emailid);
	
	@Query("select donar from DonarDetails donar where donar.bloodgroup=?1or donar.address=?1")
	List<DonarDetails> readByBloodGroupOrAddress(String filter);
	
	@Query("select donar from DonarDetails donar where donar.bloodgroup=?1 and donar.address=?2")
	List<DonarDetails> readByBloodGroupAndAddress(String bloodgroup,String address);
}
