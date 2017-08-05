package com.ta.toko.module.supplier;


import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class SupplierControllerTest {
	@Test
	public void testShowSupplierPage() throws Exception {
//		SupplierService supplierService = mock(SupplierService.class);
		SupplierController controller = new SupplierController();

		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(get("/supplier/"))
			.andExpect(view().name("supplier/supplier-entries"))
			.andExpect(model().attributeExists("suppliers"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testSaveASupplier() throws Exception {
//		SupplierService supplierService = mock(SupplierService.class);
		SupplierController controller = new SupplierController();

		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(post("/supplier/add"));
	}
}
