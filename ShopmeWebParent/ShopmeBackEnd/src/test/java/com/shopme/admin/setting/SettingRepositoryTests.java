package com.shopme.admin.setting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.setting.Setting;
import com.shopme.common.entity.setting.SettingCategory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SettingRepositoryTests {

	@Autowired SettingRepository repo;
	
	@Test
	public void testCreateGeneralSettings() {
		Setting siteName = new Setting("SITE_NAME", "Shopme", SettingCategory.GENERAL);
		Setting siteLogo = new Setting("SITE_LOGO", "Shopme.png", SettingCategory.GENERAL);
		Setting copyright = new Setting("COPYRIGHT", "Copyright (C) 2021 Shopme Ltd.", SettingCategory.GENERAL);
		
		repo.saveAll(List.of(siteName, siteLogo, copyright));
		
		Iterable<Setting> iterable = repo.findAll();
		
		assertThat(iterable).size().isGreaterThan(0);
	}
	
	@Test
	public void testCreateCurrencySettings() {
		Setting currencyId = new Setting("CURRENCY_ID", "1", SettingCategory.CURRENCY);
		Setting symbol = new Setting("CURRENCY_SYMBOL", "$", SettingCategory.CURRENCY);
		Setting symbolPosition = new Setting("CURRENCY_SYMBOL_POSITION", "before", SettingCategory.CURRENCY);
		Setting decimalPointType = new Setting("DECIMAL_POINT_TYPE", "POINT", SettingCategory.CURRENCY);
		Setting decimalDigits = new Setting("DECIMAL_DIGITS", "2", SettingCategory.CURRENCY);
		Setting thousandsPointType = new Setting("THOUSANDS_POINT_TYPE", "COMMA", SettingCategory.CURRENCY);
		
		repo.saveAll(List.of(currencyId, symbol, symbolPosition, decimalPointType, 
				decimalDigits, thousandsPointType));
		
	}
	@Test
	public void testCreateRestSettings() {
		Setting MAIL_HOST = new Setting("MAIL_HOST", "smtp.gmail.com", SettingCategory.MAIL_SERVER);
		Setting MAIL_PORT = new Setting("MAIL_PORT", "123", SettingCategory.MAIL_SERVER);
		Setting MAIL_USERNAME = new Setting("MAIL_USERNAME", "username", SettingCategory.MAIL_SERVER);
		Setting MAIL_PASSWORD = new Setting("MAIL_PASSWORD", "password", SettingCategory.MAIL_SERVER);
		Setting MAIL_FROM = new Setting("MAIL_FROM", "test@gmail.com", SettingCategory.MAIL_SERVER);
		Setting SMTP_AUTH = new Setting("SMTP_AUTH", "true", SettingCategory.MAIL_SERVER);
		Setting SMTP_SECURED = new Setting("SMTP_SECURED", "true", SettingCategory.MAIL_SERVER);
		Setting MAIL_SENDER_NAME = new Setting("MAIL_SENDER_NAME", "test", SettingCategory.MAIL_SERVER);
		Setting CUSTOMER_VERIFY_SUBJECT = new Setting("CUSTOMER_VERIFY_SUBJECT", "subject", SettingCategory.MAIL_TEMPLATES);
		Setting CUSTOMER_VERIFY_CONTENT = new Setting("CUSTOMER_VERIFY_CONTENT", "content", SettingCategory.MAIL_TEMPLATES);
		
		repo.saveAll(List.of(MAIL_HOST, MAIL_PORT, MAIL_USERNAME, MAIL_PASSWORD,MAIL_FROM,SMTP_AUTH, 
				SMTP_SECURED, MAIL_SENDER_NAME,CUSTOMER_VERIFY_SUBJECT,CUSTOMER_VERIFY_CONTENT));
		
	}
	@Test
	public void testCreatePaymentSetting() {
		Setting ORDER_CONFIRMATION_SUBJECT = new Setting("ORDER_CONFIRMATION_SUBJECT", "confirmation of order [[orderId]]", SettingCategory.MAIL_TEMPLATES);
		Setting ORDER_CONFIRMATION_CONTENT = new Setting("ORDER_CONFIRMATION_CONTENT", "Dear [[name]] your order ...", SettingCategory.MAIL_TEMPLATES);
		Setting PAYPAL_API_BASE_URL = new Setting("PAYPAL_API_BASE_URL", "https://api-m.sandbox.paypal.com", SettingCategory.PAYMENT);
		Setting PAYPAL_CLIENT_ID = new Setting("PAYPAL_CLIENT_ID", "123", SettingCategory.PAYMENT);
		Setting PAYPAL_CLIENT_SECRET = new Setting("PAYPAL_CLIENT_SECRET", "123", SettingCategory.PAYMENT);

		repo.saveAll(List.of(ORDER_CONFIRMATION_SUBJECT, ORDER_CONFIRMATION_CONTENT, PAYPAL_API_BASE_URL,
				PAYPAL_CLIENT_ID, PAYPAL_CLIENT_SECRET));
		
	}	
	@Test
	public void testListSettingsByCategory() {
		List<Setting> settings = repo.findByCategory(SettingCategory.GENERAL);
		
		settings.forEach(System.out::println);
	}
}
