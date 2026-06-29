# BÁO CÁO PHẦN 2

## Mô tả bài toán

Trong quá trình đồng bộ dữ liệu từ thiết bị IoT, hệ thống phát sinh lỗi:

`org.hibernate.TransientPropertyValueException`

Lỗi xảy ra khi lưu đối tượng `DailyReport` có tham chiếu đến `SensorDevice` vừa được tạo mới.

---

## Phân tích

Qua trao đổi với AI, nguyên nhân được xác định là `SensorDevice` đang ở trạng thái **Transient**, tức là chưa được Hibernate quản lý.

Khi gọi:

```java
reportRepo.save(report);
```

Hibernate chỉ thực hiện lưu `DailyReport`, trong khi `SensorDevice` chưa được persist nên phát sinh ngoại lệ.

---

## Giải pháp

AI đề xuất ba phương án xử lý:

1. Cấu hình `CascadeType.PERSIST`.
2. Sử dụng `CascadeType.ALL`.
3. Lưu `SensorDevice` trước bằng Repository.

Sau khi phân tích, tôi lựa chọn phương án thứ nhất vì:

* Không cần thay đổi logic của Service.
* Chỉ lan truyền thao tác lưu dữ liệu.
* Phù hợp với yêu cầu của bài toán.

---

## Ý nghĩa của CascadeType.PERSIST

`CascadeType.PERSIST` cho phép Hibernate tự động gọi `persist()` đối với các Entity liên quan khi Entity chính được lưu.

Trong trường hợp này, khi lưu `DailyReport`, Hibernate sẽ lưu `SensorDevice` trước, sau đó mới lưu `DailyReport`, từ đó tránh được lỗi `TransientPropertyValueException`.

---

## Xử lý ngoại lệ

Để tránh việc hệ thống trả về HTTP 500 mặc định, AI đề xuất bổ sung `@RestControllerAdvice` để bắt các ngoại lệ như `DataAccessException` và trả về phản hồi JSON thống nhất.

Giải pháp này giúp phía Client dễ dàng xử lý lỗi và nâng cao trải nghiệm sử dụng API.

---

## Kết luận

Sau khi áp dụng các đề xuất của AI, hệ thống xử lý đúng vòng đời của Entity trong Hibernate, đồng thời cải thiện cơ chế xử lý ngoại lệ thông qua `@RestControllerAdvice`, giúp API ổn định và thân thiện hơn với phía Client.
