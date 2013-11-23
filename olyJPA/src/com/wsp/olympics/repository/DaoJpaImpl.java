package com.wsp.olympics.repository;

import javax.persistence.EntityManager;

public class DaoJpaImpl {
	protected EntityManager em;
	
	public DaoJpaImpl(EntityManager em) {
		this.em = em;
	}
}
