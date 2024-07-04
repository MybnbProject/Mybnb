package com.project.mybnb.article.service;

import com.project.mybnb.article.domain.Article;
import com.project.mybnb.article.dto.ArticlePatchDto;
import com.project.mybnb.article.dto.ArticlePostDto;
import com.project.mybnb.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(ArticlePostDto articlePostDto){

        Article article= new Article();

        article.setTitle(articlePostDto.getTitle());
        article.setContent(articlePostDto.getContent());
        article.setCategory(articlePostDto.getCategory());
        article.setLocation(articlePostDto.getLocation());
        article.setPrice(articlePostDto.getPrice());

        return articleRepository.save(article);

    }

    public  Article updateArticle(ArticlePatchDto articlePatchDto,long article_id) throws Exception {

        Article article= findVerifiedArticle(article_id);

        article.setTitle(articlePatchDto.getTitle());
        article.setContent(articlePatchDto.getContent());
        article.setCategory(articlePatchDto.getCategory());
        article.setLocation(articlePatchDto.getLocation());
        article.setPrice(articlePatchDto.getPrice());

        return articleRepository.save(article);



    }

    //유효한 아이디인지 검증
    public Article findVerifiedArticle(long articleId) throws Exception {

        Optional<Article> findArticle=articleRepository.findById(articleId);

        return findArticle.orElseThrow(()-> new Exception("유효하지않은 숙소 아이디 입니다"));

    }


    public void deleteArtice(long articleId) throws Exception {

        Article findArticle= findVerifiedArticle(articleId); //유효한 댓글인지 검증
        articleRepository.delete(findArticle);
    }



    public Page<Article> getArticleList(int page, int size) {
        return articleRepository.findAll(PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }

    public Article getArticles(long articleId) {

        Article article= articleRepository.findById(articleId).orElse(null);

        return article;
    }
}
