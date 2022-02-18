package com.learning.utils;



import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl
{
	private final static String POSTFIX="_table";
	
	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		if(identifier ==null)
		{
			return null;
		}
		final String newName=identifier.getText()+POSTFIX;
		
		return identifier.toIdentifier(newName);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier identifier2, JdbcEnvironment context) {
		// TODO Auto-generated method 

		if(identifier2==null)
		{
			return null;
		}
		return Identifier.toIdentifier(identifier2.getText().toLowerCase());
		
	}
}
