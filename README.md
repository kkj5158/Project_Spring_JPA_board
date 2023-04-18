# Project_Spring_JPA_board
간단한 게시물 만들기 
# SpringLv1

# 1. 유스케이스

![KakaoTalk_20230415_164538938.jpg](SpringLv1%20dc6f22f12a6e48a7b9a628e23a8d5c1f/KakaoTalk_20230415_164538938.jpg)

# 2. api 명세서

[게시판 CRUD](https://www.notion.so/730a3ba5d5af4ba9b8d10760407a9650)

# 3. DB 설계

![모델링수정.PNG](SpringLv1%20dc6f22f12a6e48a7b9a628e23a8d5c1f/%25EB%25AA%25A8%25EB%258D%25B8%25EB%25A7%2581%25EC%2588%2598%25EC%25A0%2595.png)

```sql
create table board (id integer not null, created_at timestamp(6), modified_at timestamp(6), author varchar(255), content varchar(255), pw varchar(255), title varchar(255), primary key (id))
```

# 4 . 공부 요약

### 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)

<aside>
✍🏻 Body

</aside>

### 어떤 상황에 어떤 방식의 request를 써야하나요?

<aside>
✍🏻 게시판의 id를 param을 통해서 넘기는 방식을 사용하였습니다

</aside>

### RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?

<aside>
✍🏻 게시판의 id를 param을 통해서 넘기는 방식을 사용하였습니다

</aside>

### 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)

<aside>
✍🏻 게시판의 id를 param을 통해서 넘기는 방식을 사용하였습니다

</aside>

### API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!

<aside>
✍🏻 게시판의 id를 param을 통해서 넘기는 방식을 사용하였습니다

</aside>

### 5 트러블 슈팅 과정/ 버그 정리
