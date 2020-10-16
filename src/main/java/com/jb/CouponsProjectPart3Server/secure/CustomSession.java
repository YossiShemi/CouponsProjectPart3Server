package com.jb.CouponsProjectPart3Server.secure;

import java.util.Date;

import com.jb.CouponsProjectPart3Server.rest.ClientController;
import com.jb.CouponsProjectPart3Server.service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomSession {

	private ClientService clientService;
	private Date date;
	
}
