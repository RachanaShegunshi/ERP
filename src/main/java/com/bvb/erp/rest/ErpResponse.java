package com.bvb.erp.rest;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ErpResponse extends ResponseEntity<Object>{

	public ErpResponse(Object body, HttpStatusCode status) {
		super(body, status);
	}

}
