# SpringBootLearning

Proje gereksinimleri;

1) Giriş yapılabilmeli.
2) Kayıt olunabilmeli.
3) Post atılabilmeli.
4) Post'a yorum atılabilmeli.
5) Post'a beğeni atılabilmeli.
6) Bildirimler yazılabilmeli.
7) Sistem 7/24 ayakta kalabilmeli.

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
