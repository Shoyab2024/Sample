package com.donar.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.donar.entity.DonarDetails;
import com.donar.repository.DonarRepository;

@Repository
public class DonarDAO 
{
	@Autowired
	DonarRepository donarRepository;
	
	public DonarDetails insertDonarDetails(DonarDetails donarDetails) 
	{
		return donarRepository.save(donarDetails);
	}
	
	public List<DonarDetails> selectAllDonarDetails() 
	{
		return donarRepository.findAll();
	}
	
	public DonarDetails selectDonarDetails(String emailid) 
	{
		return donarRepository.findByEmailid(emailid);
	}
	
	public DonarDetails updateDonarDetails(DonarDetails donarDetails) 
	{
		return donarRepository.save(donarDetails);
	}
	
	public List<DonarDetails> searchWithBloodGroupOrAddress(String input) 
	{
		return donarRepository.readByBloodGroupOrAddress(input);
	}
	
	public List<DonarDetails> searchWithBloodGroupAndAddress(String bloodgroup,String address) 
	{
		return donarRepository.readByBloodGroupAndAddress(bloodgroup,address);
	}
	
	
}
