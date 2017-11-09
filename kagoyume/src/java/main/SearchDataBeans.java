
package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sige
 * 検索結果を持ちまわるjavabeans
 */
public class SearchDataBeans implements Serializable{
    private String resultData;
    private String imageData;
    private String priceData;
    private String detailData;
    private String janCode;
    private String rate;
    private String code;
    private String searchkey;
    private Object totalResultsAvailable;
    private Object totalResultsReturned;
    
    
    public String getResultData(){return this.resultData;}
    public void setResultData(String resultData){this.resultData = resultData;}
    
    public String getImageData(){return this.imageData;}
    public void setImageData(String imageData){this.imageData = imageData;}
    
    public String getPriceData(){return this.priceData;}
    public void setPriceData(String priceData){this.priceData = priceData;}
    
    public String getDetailData(){return this.detailData;}
    public void setDetailData(String detailData){this.detailData = detailData;}
    
    public String getRate(){return this.rate;}
    public void setRate(String rate){this.rate = rate;}
    
    public String getJanCode(){return this.janCode;}
    public void setJanCode(String janCode){this.janCode = janCode;}
    
    public String getCode(){return this.code;}
    public void setCode(String code){this.code = code;}
    
    public String getSearchkey(){return this.searchkey;}
    public void setSearchkey(String searchkey){this.searchkey = searchkey;}
    
    public Object getTotalResultsAvailable(){return this.totalResultsAvailable;}
    public void setTotalResultsAvailable(Object totalResultsAvailable){this.totalResultsAvailable = totalResultsAvailable;}
    
    public Object getTotalResultsReturned(){return this.totalResultsReturned;}
    public void setTotalResultsReturned(Object totalResultsReturned){this.totalResultsReturned = totalResultsReturned;}
}
