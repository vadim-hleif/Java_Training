package com.epam.training.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.dataaccess.dao.TransportDao;
import com.epam.training.dataaccess.dao.TransportTypeDao;
import com.epam.training.dataaccess.model.Transport;
import com.epam.training.services.TransportService;

@Service
public class TransportServiceImpl implements TransportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

	@Autowired
	TransportDao transportDao;

	@Autowired
	private TransportTypeDao transportTypeDao;

	@Override
	public void addTransport(Transport transport) {
		transport.setId(transportDao.insert(transport));
		LOGGER.info(new SimpleDateFormat().format(new Date().getTime())
				+ " Transport with reg. number " + transport.getRegistrationNumber()
				+ " added");
	}

	@Override
	public void updateTransport(Transport transport) {
		transportDao.update(transport);
	}

	@Override
	public Transport getByRegistryNumber(String number) {
		return transportDao.getByRegistryNumber(number);
	}

	@Override
	public List<Transport> getAllByRoutesNumber(Long number) {
		return transportDao.getByRouteId(number);
	}

	@Override
	public List<Transport> getAllByType(String type) {
		return transportDao.getByTypeId(transportTypeDao.getIdByType(type));
	}

	@Override
	public void deleteByRegistrationNumber(String regNumber) {
		transportDao.deleteByRegistrationNumber(regNumber);
		LOGGER.info(new SimpleDateFormat().format(new Date().getTime())
				+ " Transport with reg. number " + regNumber + " deleted");
	}
}
