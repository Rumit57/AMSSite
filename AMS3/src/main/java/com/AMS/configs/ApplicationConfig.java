package com.AMS.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {


    private String url;
    private String photoPath;
    private String importData;
    private String importUrl;

   
    public String getUrl() {

        return url;
    }

    public void setUrl(final String url) {

        this.url = url;
    }

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getImportData() {
		return importData;
	}

	public void setImportData(String importData) {
		this.importData = importData;
	}

	public String getImportUrl() {
		return importUrl;
	}

	public void setImportUrl(String importUrl) {
		this.importUrl = importUrl;
	}

  

}
