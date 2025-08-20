package com.donar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.donar.DAO.DonarDAO;
import com.donar.entity.DonarDetails;

@Service
public class DonarService 
{
	@Autowired
	DonarDAO donarDAO;
	
	public DonarDetails DonarRegistration(DonarDetails donarDetails) 
	{
		List<DonarDetails> allDonarDetails=donarDAO.selectAllDonarDetails();
		
		DonarDetails donar=new DonarDetails();
		
		int emaildCount=0;
		for (DonarDetails donarDetails2 : allDonarDetails) 
		{
			if(donarDetails2.getEmailid().equals(donarDetails.getEmailid()))
			{
				emaildCount++;
			}
		}
		if(emaildCount>0)
		{
			//exception
		}
		else
		{
			donar.setEmailid(donarDetails.getEmailid());
		}
		int mobileNumberCount=0;
		for (DonarDetails donarDetails2 : allDonarDetails) 
		{
			if(donarDetails2.getMobilenumber()==(donarDetails.getMobilenumber()))
			{
				mobileNumberCount++;
			}
		}
		if(mobileNumberCount>0)
		{
			//exception
		}
		else
		{
			donar.setMobilenumber(donarDetails.getMobilenumber());
		}
		donar.setId(donarDetails.getId());
		donar.setName(donarDetails.getName());
		donar.setAddress(donarDetails.getAddress());
		donar.setBloodgroup(donarDetails.getBloodgroup());
		donar.setGender(donarDetails.getGender());
		donar.setPassword(donarDetails.getPassword());
		
		if (donarDetails.getAge()>=18) 
		{
			donar.setAge(donarDetails.getAge());
			
		} else 
		{
			//exception
		}
		
		return donarDAO.insertDonarDetails(donar);
	}
	
	
	public List<DonarDetails> allDonarDetails() 
	{
		return donarDAO.selectAllDonarDetails();
	}
	
	public DonarDetails selectDonarDetails(String emailid,String password) 
	{
		DonarDetails donarDetails=donarDAO.selectDonarDetails(emailid);
		if(donarDetails!=null)
		{
			if(donarDetails.getPassword().equals(password)) 
			{
				return donarDetails;
			} 
			else 
			{
				return null;
			}
		}
		else 
		{
			return null;
		}
	}
	
	
	public DonarDetails updateDonarDetails(DonarDetails donarDetails) 
	{
		return donarDAO.updateDonarDetails(donarDetails);
	}
	
	public List<DonarDetails> searchWithBloodGroupOrAddress(String input)
	{
		return donarDAO.searchWithBloodGroupOrAddress(input);
	}
	
	
	
	public List<DonarDetails> searchWithBloodGroupAndAddress(String bloodgroup,String address)
	{
		return donarDAO.searchWithBloodGroupAndAddress(bloodgroup, address);
	}
}
