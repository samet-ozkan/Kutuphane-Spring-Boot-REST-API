# Kutuphane-Rest-API-Spring-Boot
Java Spring Boot (Layered Architecture) Kütüphane REST API

---
## Kullanılan Teknolojiler
* PostgreSQL
* Spring Security
* JSON Web Token
* Global Exception Handler
* [Google Books API](https://developers.google.com/books)

---
## ER Diagram

![kutuphane_veritabani](https://github.com/samet-ozkan/Kutuphane-Spring-Boot/assets/55306181/d70db211-a4f2-4324-9a75-221ae77b2109)


---
## Veritabanı
* __Account:__ Kütüphaneler ve kullanıcılar için ortak oluşturulmuş bir tablodur. Sistemdeki
hesapların e-posta, şifre ve hesap tiplerini içermektedir.
* __Kitap:__ Sistemdeki kitapların yer aldığı tablodur. Kitap adı, açıklaması, dili, isbn numarası,
sayfa sayısı ve yayın tarihi bilgilerini içermektedir.
* __Kitap - Kullanıcı:__ Kitaplar ile kullanıcıların ilişkilendirildiği tablodur. Kitabın kullanıcı tarafından
alındığı ve teslim edildiği tarihler, kitabın alınma isteğinin onaylanıp onaylanmadığı
bilgisi ve kitabın iade durumu bu tabloda yer almaktadır.
* __Kitap - Kütüphane:__ Kitaplar ile kütüphanelerin ilişkilendirildiği tablodur. Bir kitap birden fazla
kütüphanede yer alabilirken bir kütüphane de birden fazla kitaba sahip olabilir.
* __Kitap - Tür:__ Kitaplar ile kitap türlerinin ilişkilendirildiği tablodur. Bir kitap birden fazla kitap
türüne sahip olabilir.
* __Kitap - Yazar:__ Kitaplar ile yazarların ilişkilendirildiği tablodur. Bir kitap birden fazla yazara sahip
olabilir.
* __Kitap - Yorum:__ Kitaplara yapılan yorumların yer aldığı tablodur. Yorum yapılan kitap ile yorum yapan
kullanıcının id bilgileri tutulur. Yorum metni ve bu metnin kitapla ilgili spoiler içerip
içermediği bilgileri de bu tabloda bulunmaktadır.
* __Kullanıcı:__ Kullanıcıların ad, soyad ve hesap bilgilerinin tutulduğu tablodur.
* __Kütüphane:__ Kütüphanelerin ad, adres, şehir, teslim süresi ve hesap bilgilerinin tutulduğu tablodur.
* __Kütüphane - Yorum:__ Kütüphanelere yapılan yorumların bulunduğu tablodur. Yorum yapılan kütüphaneye
ve yorum yapan kullanıcıya referans verilmektedir.
* __Tür:__ Kitap türlerinin yer aldığı tablodur.
* __Yazar:__ Yazarların ad ve soyad bilgisinin tutulduğu tablodur.
* __Refresh Token:__ JWT implementasyonu için refresh tokenlerin yer aldığı tablodur.

---
## Account Types
* __Admin:__ 'admin'
* __Kütüphane:__ 'kutuphane'
* __Kullanıcı:__ 'kullanici'

---
## application.properties

```properties
# Uygulama Ayarları
spring.application.name=Kutuphane Uygulamasi

# JWT Ayarları
jwt.token.validity=900000
jwt.refreshtoken.validity=86400000
jwt.signing.key=YOUR_SIGNING_KEY
jwt.authorities.key=roles
jwt.token.prefix=Bearer
jwt.header.string=Authorization

# PostgreSQL Veritabanı Ayarları
spring.datasource.url=jdbc:postgresql://localhost:5432/kutuphane
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update

# Kütüphane Kaydı İçin Doğrulama Kodu
verification.code=YOUR_VERIFICATION_CODE
```

---
## İletişim
__Email:__ <a href="mailto:samet-ozkan@outlook.com">samet-ozkan@outlook.com</a>







