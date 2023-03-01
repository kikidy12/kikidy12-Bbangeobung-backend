# Bbangeobung-backend <img src="https://user-images.githubusercontent.com/95565436/222117045-2eace948-83c6-4b96-bf4e-a14cf47265a0.png" width="50px">


## 🍞Team
- - - -
| 권성민 | 이선옥 | 김동민 | 손채이 | 이한결 | 이현동 | 최하영 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| BE  |  BE   |  BE   |  BE   | FE  |  FE   |  FE   |


## 📋프로젝트 기능
- - - -
|      기능      | 설명                                                           |
|:------------:|:-------------------------------------------------------------|
|     회원가입     | 아이디, 비밀번호를 유저권한으로 등록합니다.                                     |
|     로그인      | 아이디와 비밀번호로 로그인합니다.                                           |
|   회원정보 조회    | 토큰으로 유저정보를 조회합니다.                                            |
|   회원정보 수정    | 이름과 비밀번호를 수정합니다. 비밀번호를 수정하려면 현재 비밀번호와 변경할 비밀번호 둘 다 기입해야 합니다. |
|    상점 등록     | 붕어빵 가게 정보를 등록합니다.                                            |
|    상점 삭제     | 가게를 등록한 사람과 관리자만 삭제 가능합니다.                                   |
| 상점 목록(검색 기능) | 붕어빵 종류별 가격정렬 가능합니다.(id기반), fIshBredTypeId = 0이면 전체조회         |
|   상점 단일 조회   | 상점 단일 조회                                                     |
|   상점 목록v2    | 붕어빵 종류별 가격정렬 가능합니다.(text기반), itemName이 공백 혹은 Null이면 전체조회     |
|  내 상점 목록 조회  | 유저가 등록한 상점 목록                                                |
|  상점 단일 조회v2  | 상점 단일 조회                                                     |
|  붕어빵 종류 등록   | 붕어빵 종류 등록(관리자만 가능)                                           |
|  붕어빵 종류 수정   | 붕어빵 종류 수정(관리자만 가능)                                           |
|  붕어빵 종류 삭제   | 붕어빵 종류 삭제(관리자만 가능)                                           |
| 붕어빵 종류 목록 조회 | 붕어빵 종류 목록 조회                                                 |
| 붕어빵 종류 단일 조회 | 붕어빵 종류 단일 조회                                                 |
|    좋아요 기능    | 좋아요를 한 사람만 취소 가능합니다.                                         |
|    리뷰 등록     | 이미지 첨부가 가능한 리뷰 등록 기능                                         |
|    리뷰 수정     | 리뷰를 작성한 유저만 수정 가능                                            |
|    리뷰 삭제     | 리뷰를 작성한 유저만 삭제 가능                                            |
|  리뷰 리스트 조회   | 상점에 달린 리뷰 목록을 조회                                             |
|   리뷰 단일 조회   | 리뷰 하나를 조회 가능                                                 |
| (관리자) 리뷰 삭제  | 관리자가 리뷰를 삭제합니다.                                              |
|    장소 신고     | 장소를 신고합니다.                                                   |
|    리뷰 신고     | 리뷰를 신고합니다.                                                   |
| 신고내역 조회(장소)  | 관리자만 가능합니다.                                                  |
| 신고내역 조회(리뷰)  | 관리자만 가능합니다.                                                  |
|    댓글 등록     | 가게에 대한 댓글을 등록합니다.                                            |
|    댓글 수정     | 작성한 댓글을 수정합니다. (해당 유저만 가능)                                   |
|    댓글 삭제     | 작성한 댓글을 삭제합니다. (해당 유저만 가능)                                   |
|   댓글 목록 조회   | 해당 가게에 있는 댓글 리스트 조회입니다.                                      |
|    댓글 신고     | 유저가 댓글을 신고하는 기능입니다.                                          |
| 신고 받은 댓글 조회  | 관리자가 신고 받은 댓글 전체를 조회할 수 있습니다.                                |
| 신고 받은 댓글 삭제  | 관리자가 신고 받은 댓글을 삭제할 수 있습니다.                                   |


## 📁ERD
- - - -
<img src="https://bbangeobung.s3.ap-northeast-2.amazonaws.com/bbangeobung.png">


## 🖥Technologies & Tools (BE)
- - - -
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"/> <img src="https://img.shields.io/badge/JSONWebToken-000000?style=for-the-badge&logo=JSONWebTokens&logoColor=white"/>
<img src="https://img.shields.io/badge/MariaDB -4479A1?style=for-the-badge&logo=MariaDb&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"/>
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/>
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"/>
<img src="https://img.shields.io/badge/cloudtype-FCC624?style=for-the-badge"/>
<img src="https://img.shields.io/badge/AmazonEC2-FF9900?style=for-the-badge&logo=AmazonEC2&logoColor=white"/>
<img src="https://img.shields.io/badge/AmazonS3-569A31?style=for-the-badge&logo=AmazonS3&logoColor=white"/>
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"/>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/> 

<img src="https://img.shields.io/badge/IntelliJIDEA-000000?style=for-the-badge&logo=IntelliJIDEA&logoColor=white"/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"/>
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/>
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"/>
