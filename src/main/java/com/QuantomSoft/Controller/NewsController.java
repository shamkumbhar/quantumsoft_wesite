package com.QuantomSoft.Controller;

import com.QuantomSoft.Entity.News;
import com.QuantomSoft.Exception.NewsNotFound;
import com.QuantomSoft.Service.NewsService;
import com.QuantomSoft.ServiceImpl.ContactServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/News")
@CrossOrigin("*")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private NewsService newsService;

    @PostMapping("/SaveNews")
    public ResponseEntity<News> SaveNews(@RequestBody News news){
        logger.info("Received request to save news");
        try{


            News saveNews=newsService.saveNews(news);
            return new ResponseEntity<>(saveNews, HttpStatus.CREATED);
        }catch(Exception e){
            logger.error("Error While Creating News {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getNewsById/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        try {
            logger.info("Request for getting News with id {}", id);
            News news = newsService.getNewsByNewsId(id);
            return ResponseEntity.ok(news);
        } catch (NewsNotFound e) {
            logger.error("News not found with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/allnews")
    public ResponseEntity<List<News>> getAllnews(){
        try{
            logger.info("Fetching all news");
            List<News> AllNews=newsService.getAllNews();
            return new  ResponseEntity<>(AllNews,HttpStatus.OK);
        }catch(NewsNotFound e){
            logger.info("error while fetching all news",e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getNewsByAdminId/{adminId}")
    public ResponseEntity<News> getNewsByAdminId(@PathVariable Long adminId) {
        try {
            logger.info("Request for getting News with adminId {}", adminId);
            News news = newsService.getNewsByAdminId(adminId);
            return ResponseEntity.ok(news);
        } catch (NewsNotFound e) {
            logger.error("News not found for adminId {}: {}", adminId, e.getMessage(), e);
            return ResponseEntity.status(404).build();
        }
    }



    @PutMapping("/update/{newsId}")
    public ResponseEntity<News> updateNews(@PathVariable Long newsId,@RequestBody
                                           News news) {
        try {
            News updatedNews = newsService.updateNewsByNewsId(newsId, news);
            return new ResponseEntity<>(updatedNews, HttpStatus.OK);
        } catch (NewsNotFound e) {
            logger.error("Error while updating news for adminId {}: {}", newsId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error while updating news {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long newsId) {
        try {
            newsService.deleteNewsByNewsId(newsId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NewsNotFound e) {
            logger.error("Error while deleting news for adminId {}: {}", newsId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error while deleting news {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
