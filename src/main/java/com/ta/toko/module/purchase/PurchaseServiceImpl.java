package com.ta.toko.module.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.toko.dao.PurchaseDao;
import com.ta.toko.entity.Product;
import com.ta.toko.entity.Purchase;
import com.ta.toko.entity.PurchasedDetail;
import com.ta.toko.module.product.ProductService;
import com.ta.toko.module.purchase.model.ProductLineInfo;
import com.ta.toko.module.purchase.model.PurchaseInfo;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private ProductService productService;

	@Override
	public void save(PurchaseInfo purchase) {
		Purchase newPurchase = new Purchase();
		newPurchase.setDetails(purchase.getDetails());
		newPurchase.setPruchaseNo(purchase.getPurchaseNo());
		newPurchase.setSupplier(purchase.getSupplier());
		newPurchase.setTotalPurchased(purchase.getTotalPurchased());
		newPurchase.setPurchasedDetails(new ArrayList<PurchasedDetail>());
		newPurchase.setPurchaseDate(purchase.getPurchaseDate());
		logger.info(newPurchase.getPurchaseDate() + "");

		List<Product> updateProduct = new ArrayList<Product>();

		for (ProductLineInfo pLine : purchase.getProductLineInfos()) {
			PurchasedDetail pDetail = new PurchasedDetail();
			pDetail.setProduct(pLine.getProduct());
			pDetail.setPurchase(newPurchase);
			pDetail.setPurchasePrice(pLine.getPurchasePrice());
			pDetail.setQuantity(pLine.getQuantity());
			pDetail.setSellingPrice(pLine.getSalePrice());
			pDetail.setTotalItem(pLine.getTotalItem());
			newPurchase.getPurchasedDetails().add(pDetail);

			Product p = pDetail.getProduct();
			logger.info("Name: " + p.getName() + ", current price: " + p.getSalesPrice() + ", current quantity: "
					+ p.getQuantity());
			// logger.info("current quantity: " + p.getQuantity());
			p.setSalesPrice(pDetail.getSellingPrice());
			p.setQuantity(p.getQuantity() + pDetail.getQuantity());
			updateProduct.add(p);
			logger.info("after update price: " + p.getSalesPrice() + ", after update quantity: " + p.getQuantity());
			// p.set

		}
		logger.info("supplier: " + newPurchase.getSupplier().toString());
		purchaseDao.save(newPurchase);

		for (Product product : updateProduct) {
			productService.update(product);
		}
	}

	@Override
	public List<PurchaseInfo> searchPurchase(Date from, Date to) {
		List<Purchase> purchases = purchaseDao.getReport(from, to);
		List<PurchaseInfo> purchaseInfos = new ArrayList<PurchaseInfo>();

		logger.info("purchase size: {}", purchases.size());

		for (Purchase prc : purchases) {
			PurchaseInfo pinfo = new PurchaseInfo();
			pinfo.setPurchaseNo(prc.getPruchaseNo());
			pinfo.setDetails(prc.getDetails());
			pinfo.setSupplier(prc.getSupplier());
			pinfo.setPurchaseDate(prc.getPurchaseDate());
			pinfo.setTotalPurchased(prc.getTotalPurchased());
			pinfo.setProductLineInfos(new ArrayList<ProductLineInfo>());
			logger.info("purchase details: {}", prc.getPurchasedDetails().size());
			for (PurchasedDetail prcDetail : prc.getPurchasedDetails()) {
				ProductLineInfo pLine = new ProductLineInfo();
				pLine.setProduct(prcDetail.getProduct());
				pLine.setPurchasePrice(prcDetail.getPurchasePrice());
				pLine.setTotalItem(prcDetail.getTotalItem());
				pLine.setQuantity(prcDetail.getQuantity());

				pinfo.getProductLineInfos().add(pLine);
			}

			purchaseInfos.add(pinfo);

		}

		return purchaseInfos;
	}

}
