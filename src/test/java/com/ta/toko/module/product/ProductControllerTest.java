package com.ta.toko.module.product;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.ta.toko.config.RootConfig;
import com.ta.toko.config.WebAppInitializer;
import com.ta.toko.module.product.web.ProductController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, WebAppInitializer.class })
@WebAppConfiguration
public class ProductControllerTest {
	
	@Autowired
	private ProductController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = standaloneSetup(controller).build();
	}
	//TODO ENSURE THESE ON EACH TEST UNIT
	/*
	 * 1. Ensure that model attribute called todos has two items in it
	 * 2. Ensure that the model attribute called todos contains the correct items.
	 * 3. Verify that the findAll() method of our mock object was called only once.
	 * 4. Ensure that other methods of the mock object were not called during the test.
	 */

	@Test
	public void testShowProductPage() throws Exception {		
		mockMvc.perform(get("/product/"))
			.andExpect(status().isOk())
			.andExpect(view().name("product/product-entries"))
			.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void testShowAddProductPage() throws Exception {		
		mockMvc.perform(get("/product/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("product/product"))
			.andExpect(model().attributeExists("product"));;
	}
	
	@Test
	public void testSearchProduct() throws Exception {		
		mockMvc.perform(post("/product/search").param("barcode", "barcode").param("name", "Name"))
			.andExpect(status().isOk())
			.andExpect(view().name("product/product-entries"))
			.andExpect(model().attributeExists("products"));;
	}
	
	@Test
	public void testAddProduct() throws Exception {		
		mockMvc.perform(post("/product/add").param("barcode", "barcode").param("name", "Name"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/product"))
			.andExpect(flash().attributeExists("alert"))
			.andExpect(flash().attributeExists("alertMessage"));
	}
	
	@Test
	public void testAddProductHasFieldError() throws Exception {		
		mockMvc.perform(post("/product/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("product/product"))
			.andExpect(model().attributeHasFieldErrors("product", "name"))
			.andExpect(model().attributeHasFieldErrors("product", "barcode"));
	}
	
	@Test
	public void testShowProductDetailPage() throws Exception {		
		mockMvc.perform(get("/product/{id}",1L))
			.andExpect(status().isOk())
			.andExpect(view().name("product/product"))
			.andExpect(model().attributeExists("product"))
			.andExpect(model().attributeExists("actionUrl"));
	}
	
	@Test
	public void testEditProduct() throws Exception {		
		mockMvc.perform(post("/product/{id}", 1L).param("barcode", "barcode").param("name", "Name"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/product"))
			.andExpect(flash().attributeExists("alert"))
			.andExpect(flash().attributeExists("alertMessage"));
	}
	
	@Test
	public void testDeleteProduct() throws Exception {		
		mockMvc.perform(get("/product/delete/{id}",1L))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/product"))
			.andExpect(flash().attributeExists("alert"))
			.andExpect(flash().attributeExists("alertMessage"));
	}
}
