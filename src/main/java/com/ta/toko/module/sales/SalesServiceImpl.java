package com.ta.toko.module.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.toko.dao.ProductDao;
import com.ta.toko.dao.SalesDao;
import com.ta.toko.entity.Product;
import com.ta.toko.entity.SaleDetail;
import com.ta.toko.entity.Sales;
import com.ta.toko.module.sales.model.ProductLine;
import com.ta.toko.module.sales.model.SalesInformation;

@Service
public class SalesServiceImpl implements SalesService {
	private static Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);

	@Autowired
	private SalesDao salesDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public void save(SalesInformation salesInformation) {
		Sales sales = new Sales();
		sales.setNo(salesInformation.getNo());
		sales.setSaleDate(salesInformation.getSalesDate());
		sales.setTotalSale(salesInformation.getTotalSales());
		sales.setSaleDetails(new ArrayList<SaleDetail>());

		List<Product> listProduct = new ArrayList<Product>();

		for (ProductLine pLine : salesInformation.getProductLines()) {
			SaleDetail sDetail = new SaleDetail();
			sDetail.setProduct(pLine.getProduct());
			sDetail.setQuantity(pLine.getQuantity());
			sDetail.setSales(sales);
			sDetail.setSubTotal(pLine.getSubTotal());

			sales.getSaleDetails().add(sDetail);

			Product updateProdut = pLine.getProduct();
			logger.info("Product guantity: {}", updateProdut.getQuantity());
			updateProdut.setQuantity(updateProdut.getQuantity() - sDetail.getQuantity());
			logger.info("Product guantity: {}", updateProdut.getQuantity());

			listProduct.add(updateProdut);
		}

		salesDao.save(sales);

		for (Product p : listProduct) {
			productDao.update(p);
		}
	}

	@Override
	public List<SalesInformation> searchSales(Date from, Date to) {
		List<Sales> sales = salesDao.getReport(from, to);
		List<SalesInformation> salesInformations = new ArrayList<SalesInformation>();

		for (Sales s : sales) {
			SalesInformation sInfo = new SalesInformation();
			sInfo.setNo(s.getNo());
			sInfo.setSalesDate(s.getSaleDate());
			sInfo.setTotalSales(s.getTotalSale());
			sInfo.setProductLines(new ArrayList<ProductLine>());

			for (SaleDetail sDetail : s.getSaleDetails()) {
				ProductLine pLine = new ProductLine();
				pLine.setProduct(sDetail.getProduct());
				pLine.setQuantity(sDetail.getQuantity());
				pLine.setSubTotal(sDetail.getSubTotal());

				sInfo.getProductLines().add(pLine);
			}

			salesInformations.add(sInfo);
		}
		return salesInformations;
	}

}
