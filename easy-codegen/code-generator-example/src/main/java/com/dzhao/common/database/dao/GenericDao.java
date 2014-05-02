package com.dzhao.common.database.dao;

import java.io.Serializable;

public interface GenericDao <T, ID extends Serializable>{
	/** Persist the newInstance object into database */
	T save(T t);

	/** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
	T get(ID id);
	
	/** Save changes made to a persistent object.  */
	T update(T t);
	
	/** Remove an object from persistent storage in the database */
	void delete(T t);
}
