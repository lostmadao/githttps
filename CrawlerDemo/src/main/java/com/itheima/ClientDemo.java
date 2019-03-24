package com.itheima;

import com.itheima.dao.RouteDao;
import com.itheima.domain.Route;
import com.itheima.utils.DateUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

public class ClientDemo {
    private static RouteDao routeDao = new RouteDao();

    public static void main(String[] args) {
        try {
            String keyword = "国内游";
            keyword = URLEncoder.encode(keyword);
//        搜索查询结果：http://www.jinmalvyou.com/search/index/view_type/1/keyword/%E5%9B%BD%E5%86%85  默认为第一页
            Document document = Jsoup.connect("http://www.jinmalvyou.com/search/index/view_type/1/keyword/" + keyword).get();
//        获取末页
            Element endPageElement = document.selectFirst(".page-block div .end");
            String endPageHref = endPageElement.attr("href");
            int endPage = Integer.parseInt(endPageHref.substring(endPageHref.lastIndexOf("/") + 1, endPageHref.lastIndexOf("/") + 3));

//        从第一页数据爬到末页
            for (int i = 1; i <= 36; i++) {
                System.out.println(i);
                if (i > 1) {
                    //                其他页
                    document = Jsoup.connect("http://www.jinmalvyou.com/search/index/view_type/1/keyword/" + keyword + "/p/" + i + ".html").get();
                }

                //        获取每一个路线标签
                Elements routeElements = document.select(".rl-b-li");

                for (Element routeElement : routeElements) {
                    //            每一个routeElements代表一条路线标签
                    Route route = new Route();

                    //        dao需要什么数据，我们就封装什么数据给他

                    //            封装rname
                    Element aElement = routeElement.selectFirst(".pro-title a");
                    String rname = aElement.text();
                    route.setRname(rname);

                    //            封装价格
                    Element priceElement = routeElement.selectFirst(".price strong");
                    double price = Double.parseDouble(priceElement.text());
                    route.setPrice(price);

                    //            封装线路介绍
                    Element introduceElement = routeElement.selectFirst(".pro-colomn");
                    String routeIntroduce = introduceElement.text();
                    route.setRouteIntroduce(routeIntroduce);

                    //              是否上架
                    route.setRflag("1");

                    //              上架时间
                    String dateStr = DateUtil.dateParse(new Date());
                    route.setRdate(dateStr);

                    //              是否主题旅游
                    route.setIsThemeTour("1");

                    //              收藏数量(默认为0)
                    route.setCount(0);

                    //              所属分类(国内游设置cid为5)
                    route.setCid(5);

                    //             抓取数据的来源id
                    Element imgaElement = routeElement.selectFirst(".pro-img a");
                    String href = imgaElement.attr("href");
                    String sourceID = href.substring(href.lastIndexOf("=") + 1);
                    route.setSourceId(sourceID);

                    //              缩略图
                    Element imgElement = imgaElement.selectFirst("img");
                    String src = "http:" + imgElement.attr("src");    //完整路径
                    //            存取到数据库格式："img/product/small/"  加上文件名称
                    String imgname = src.substring(src.lastIndexOf("/") + 1);
                    String rimage = "img/product/small/" + imgname;
                    route.setRimage(rimage);

//                    下载图片
                   imgDownload(src,imgname);

                    //              所属商家
                    route.setSid(1);

                    routeDao.updateRoutetTwo(route);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片下载
     * @param src
     * @param imgname
     */
    private static void imgDownload(String src,String imgname) {
        //            根据完整图片路径下载图片
        //            创建客户端
        InputStream in = null;
        OutputStream out = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //            创建请求
//                    System.out.println(src);
            HttpGet httpGet = new HttpGet(src);
            //            执行请求,获取服务器发送过来的响应（不能使用post请求，不然图片无法打开）
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//                    获取响应状态码
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                //                        响应正常，获取响应体
                //                   获取响应体中的数据,转换成输入流
                HttpEntity entity = httpResponse.getEntity();
                in = entity.getContent();

                out = new BufferedOutputStream(new FileOutputStream("d:/heimatravel/" + imgname));
                IOUtils.copy(in, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
