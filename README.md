# SpringBootLearning

Proje gereksinimleri;

1) Login
2) Register
3) Create post
4) Create comment for post
5) Create like for post
6) Read noti.
7) Full safety

Image'de bulunan db password'u ve application.propertieste bulunan password ve app_tokenler localde çalıştığı için dikkate alınmamıştır :)
## ApiDesign

./users
./users/{userId}
./users/{userId}/profile

./posts
./posts?user={userId}
./posts/{postId}

./comments
./comments?postId={postId}
./comments?user={userId}
./comments/{commentId}

./likes
./likes?postId={postId}
./likes?user={userId}
./likes/{likeId}
