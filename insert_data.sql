use iquiz;

insert into `iquiz`.`role`
(`role_id`,
`role`,
`role_description`) VALUES
(1,
'customer',
'dan thuong'),
(2,
'expert',
'tao quiz'),
(3,
'admin',
'trum cuoi'),
(4,
'marketing',
'trum cuoi');

INSERT INTO `iquiz`.`category`
(`id`,
`catname`)
VALUES
(1, 'Mathematics' ),
(2, 'English' ),
(3, 'Programming');

INSERT INTO `iquiz`.`user`
(`user_id`,
`fullname`,                                                                                                             
`Email`,
`Gender`,
`Mobile`,
`Password`,
`role_id`)
VALUES
(1, 'Nguyen Anh Quan', 'forwork.quanna@gmail.com', 1, '0379039267', 'deptrai', 3),
(2, 'Nguyen Van A', 'ntnm2003@gmail.com', 1, '012345678', 'ahaha', 1),
(3, 'Nguyen Thi B', 'dungnpn28@gmail.com', 0, '0987654321', 'bebebe', 1),
(4, 'Mr. Expert1', 'quannahe176660@gmail.com', 1, '068686868', 'hocgioi', 2),
(5, 'Mr. Expert2', 'maintnhe176347@gmail.com', 0, '0973600757', 'xinhgai', 2),
(6, 'Mr. Expert3', 'expert3@gmail.com', 1, '0978779519', 'numberone', 2),
(7, 'Lung Thi Linh', 'lunglinh2003@gmail.com', 0, '0982712755', 'yeudoi', 4);


INSERT INTO `iquiz`.`subject`
(`idcourse`,
`idcategory`,
`expert_id`,
`course_name`,
`datestart`)
VALUES
(1, 1, 4, 'Mathematics for Engineering', '2023-05-19'),
(2, 1, 4, 'Statistics & Probability', '2023-05-22'),
(3, 2, 5, 'IELTS Speaking - Road to 6.5', '2023-04-30'),
(4, 2, 5, 'Master TOEIC in 2 Hours', '2023-05-19'),
(5, 3, 6, 'How to build for first Website - Full Course for Beginners', '2023-05-15'),
(6, 3, 6, 'Master Spring Boot 3 & Spring Framework 6 with Java', '2023-05-01');

INSERT INTO `iquiz`.`post_category`
(`post_category_id`,
`post_category`,
`post_category_description`)
VALUES
(1, 'Academic', 'Academic Posts/Blogs'),
(2, 'Life/Study Advices', 'Life/Study Advices Posts/Blogs'),
(3, 'Advertising', 'Advertising Posts/Blogs'),
(4, 'Announcement', 'Announcement Posts/Blogs');

INSERT INTO `iquiz`.`slider`
(`slider_id`,
`user_id`,
`title`,
`image`,
`backlink`,
`status`,
`visibility`)
VALUES
(1 , 7, 'Become a Full Stack Software Engineer with The Hottest Technologies Right Now', 'slider1.png', 'http://localhost:8080/course-detail?id=5', 'active', 1),
(2 , 7, 'Anyone can get 6.5 IELTS!', 'slider2.png', 'http://localhost:8080/course-detail?id=3', 'active', 1),
(3 , 7, 'First Step to become a BA - Statistics and Probability', 'slider3.png', 'http://localhost:8080/course-detail?id=2', 'active', 1);

INSERT INTO `iquiz`.`posts`
(`post_id`,
`user_id`,
`thumbnail`,
`post_category_id`,
`title`,
`updated_date`,
`author`,
`brief_infor`,
`description`,
`status`,
`featuring`)
VALUES
(1, 4, 'post1_thumbnail.png', 2, '"Bánh quy" - món ngon cho người hay bỏ cuộc', '2023-05-19', 'Thương', 'Phương pháp "hũ bánh quy" - liệu pháp tâm lý cho những người hay bỏ cuộc.', 
'Năm 2022, sau khi đại dịch tạm qua đi, tôi quyết tâm từ nay phải sống thật khỏe mạnh. Từ một đứa chây ì vận động, tôi bắt bản thân mình tập mỗi ngày ít nhất 10 phút, xem như là để khởi đầu. Mỗi ngày, tôi lăn ra khỏi giường (theo đúng nghĩa đen), và bắt đầu với plank 2 phút, gập bụng, động tác đạp xe, và squat một chút. Tôi giới hạn lượng đường nạp vào người mỗi ngày, tập chế độ ăn 8/16. Cố gắng thiền mỗi ngày 15 phút.
Có lẽ vì nhìn thấy tôi đã giác ngộ, ông bà tổ tiên trên cao mỉm cười yên tâm, bỏ đi du lịch, không “gánh” tôi nữa.
Nên, đến tháng tư năm 2022, tôi bị covid. Cái cảm giác bải hoải, uể oải, mệt mỏi, cơn sốt nhẹ, chứng mất mùi, và quan trọng hơn hết là cái suy nghĩ “mình đang bệnh mà” khiến tôi hết muốn tiếp tục tập thể dục mỗi ngày.
Trùng hợp thay, những video self-help tôi xem trên youtube dẫn tôi đến với video nói về phương pháp hũ bánh quy, tên tiếng Anh là Cookie Jar Method. 
Nói cho nhanh gọn và dễ hiểu, thì “cookie” ở đây là những thành tựu mà bạn đã đạt được trong quá khứ, khiến bạn tự hào và tự tin về bản thân mình. Bạn thu thập chúng và bỏ và một cái hũ tưởng tượng trong đầu bạn. Mỗi khi gặp khó khăn trong việc tiếp tục theo đuổi việc gì đó (như tập thể dục mỗi ngày chẳng hạn), thì bạn lấy ra một cái bánh “cookie”, nhớ về nó, chiêm nghiệm nó, cảm giác thành tựu và tự hào sẽ là chất dinh dưỡng cho hành trình tiếp theo của bạn trên con đường theo đuổi việc khó khăn kia. 
Tôi nghĩ về cookie jar của mình. Và bàng hoàng phát hiện ra, mình chẳng có cái cookie nào liên quan đến vận động thể chất để lấy ra dùng cả. Tôi (đã) là một đứa lười vận động. Siêu lười vận động. Không hề quá đáng khi nói rằng tôi là đại thần trong giới lười vận động. Tôi lười vận động và anti chuyện vận động tới nỗi: ngày xưa khi đọc cuốn Luật Trí Não, tôi bỏ đọc ngay chương đầu tiên, bởi vì nội dung của chương đầu tiên là “thể dục thể thao giúp ích cho hoạt động trí não”. Tôi cứ tự an ủi mình rằng, người ta nói “đầu óc ngu si thì tứ chi phát triển”, tứ chi mình èo uột, nên hẳn là đầu óc mình không ngu lắm. (Đúng, bạn nghĩ đúng đấy, “cái logic vớ vẩn gì đây!”)
Nhưng lời an ủi đó mất dần tác dụng theo năm tháng và lộ rõ bản chất chỉ là một lời ngụy biện rẻ tiền khi tôi nhìn thấy các nhà khoa học thần kinh hàng đầu thế giới trong một cơ thể khỏe mạnh, ai cũng vận động thể chất, ai cũng đẹp đẽ và săn chắc. Thành ra, tôi nhìn lại mình và nghĩ, chậc, đầu óc vốn đã không thông minh bằng ai, thà cho tứ chi phát triển, chứ đầu óc ngu si mà tứ chi còn èo uột thì thật sự khốn khổ quá. 
Và, ding-dong. Tôi nghĩ ra rồi.
Thay vì tìm “cookie”, tôi quyết định tự làm ra một cái  “cookie” mới tinh, thơm ngon bổ dưỡng cho chính mình. Dù covid hành tôi sốt lên sốt xuống, mất mùi, uể oải, tôi vẫn buộc mình tập thể dục 10 phút một ngày. 
“Thứ gì tốt, thì phiên bản nhỏ hơn của nó cũng tốt”. 
Nghĩa là, tập thể dục tốt, 60 phút tốt, thì 6 phút cũng tốt (ít tốt hơn nhưng vẫn tốt). 
Và, đến hiện tại.
Mỗi khi bệnh lười vận động của tôi trở nặng, thì tôi lại có cái cookie đó, “ngay cả khi covid, mình cũng tập được, thì tại sao giờ khỏe mạnh, lại không tập được.” Cái cookie này đối với tôi là một món thơm lừng, ngọt ngào và đầy dinh dưỡng, nó khiến tôi lập tức lê thân đứng dậy để tập thể dục.
Vậy, dù đã quá rõ ràng, chúng ta vẫn nên nói một chút về các bước cơ bản của phương pháp hũ bánh quy này để kết bài: 
Bước 1: làm bánh - tạo ra một hũ bánh quy (cookie jar) bằng cách liệt kê những thành tự trong quá khứ khiến bạn tự hào. Và, hey, nếu như bạn chưa có mẩu bánh quy nào phù hợp với tình huống (như mối quan hệ thù địch của tôi và hoạt động thể chất trước đây), thì bạn hoàn toàn có thể tự nhủ rằng việc tiếp tục cố gắng – bản thân nó chính là một chiếc bánh quy mà bạn đang nướng để dành cho tương lai.
Bước 2: ăn bánh. Hãy chiêm nghiệm về những thành tựu quá khứ.
Có một câu nói rằng: lý trí là một đứa trẻ hay khóc nhè và hay đòi hỏi, nó cứ liên tục gào khóc và rên rỉ để làm những điều không tốt, như mua sắm vô độ, ngủ nướng, ăn đồ ngọt, uống đồ có cồn… Và với tư cách một người lớn, bạn sẽ chiều theo đứa trẻ hay bảo nó im đi, phớt lờ nó và làm điều mà bạn muốn làm? Việc chiêm nghiệm các thành tựu quá khứ (ăn bánh) khiến cho đứa trẻ lý trí hư đốn kia im lặng, không rên rỉ nữa, bạn có thể tưởng tượng rằng có một đứa trẻ khác
ngoan hơn, hiểu biết hơn (cookie), đang thuyết phục nó. Trẻ nói chuyện với trẻ thì hiệu quả hơn người lớn dùng lý lẽ để nói chứ, đúng không? 
Hết rồi đó. Đây là phương pháp đơn giản nhất, không mất thời gian, không mất công sức, không mất tiền.
Cuối cùng, nếu bạn đọc bài viết đến đây rồi tự hỏi, rồi công thức bánh quy đâu? thì tôi xin lỗi đã làm bạn thất vọng. Tôi chỉ có thể đề xuất bạn tự google thử xem sao.', 
'uploaded', 1 ),

(2, 4, 'post2_thumbnail.png', 1, 'ĐỌC VỚI NOTECARD: PHƯƠNG PHÁP ĐỌC SÁCH HIỆU QUẢ VÀ NHỚ LÂU', '2023-05-19', 'Chi Nguyễn', 'Phương pháp đọc đã làm thay đổi cuộc đời mình, khiến cho mình nhớ và ứng dụng kiến thức từ sách tốt hơn trước rất nhiều!', 
'Ai cũng biết mục đích quan trọng nhất của việc đọc sách là rút ra được những bài học giá trị, tinh hoa từ những cuốn sách. Vì thế, ta mới có những quy trình như đọc chủ động bằng việc ghi chép, gạch chân trong quá trình đọc và đọc ứng dụng bằng cách tóm tắt, ghi lại ít nhất 3 bài học mình có được từ cuốn sách…
Tuy nhiên, câu hỏi đặt ra là: Làm sao có thể hệ thống hóa những bài học này để mỗi lần gặp băn khoăn nào đó, ta không phải đọc lại toàn bộ cuốn sách, tìm lại từng chỗ ghi chú? Và quan trọng hơn, làm sao ta có thể kết nối những bài học khác nhau từ nhiều cuốn sách để đưa ra ứng dụng đa chiều nhất vào thực tế của mình?
Khoảng 2 tháng gần đây, mình thử nghiệm một phương pháp ghi chú khi đọc sách sử dụng notecard (thẻ ghi chú). Thực sự, phương pháp này đã làm thay đổi cuộc đời mình, khiến cho mình nhớ và ứng dụng kiến thức từ sách tốt hơn trước rất nhiều.

1- NGUỒN GỐC
Trước hết, mình muốn nói lời cảm ơn tới người đã cho mình phương pháp ghi chú bằng note card này. Đó là Ryan Holiday, một tác giả tài năng người Mỹ với nhiều cuốn sách best-seller về chủ đề Marketing và Chủ nghĩa Khắc kỷ. Ryan Holiday cũng nói rằng anh ấy học được phương pháp này từ mentor (người hướng dẫn) của mình là cây bút nổi tiếng Robert Greene. Và Robert Green có thể cũng đã học phương pháp này từ người khác…
So sánh với phương pháp gốc được miêu tả trong bài blog của Ryan Holiday, mình đã thay đổi một số chi tiết để giản lược, tối ưu hóa phương pháp này cho phù hợp nhu cầu sử dụng của riêng mình (một người làm nghiên cứu, sáng tạo nội dung, tác giả sách). Vì vậy, trong quá trình học và thử nghiệm phương pháp này, bạn hoàn toàn có thể thay đổi để tăng tính ứng dụng cao hơn theo yêu cầu của riêng mình.
Phương pháp đọc sách dưới đây được phát triển từ góc nhìn cá nhân của mình, không mang tính công thức gò bó hay áp đặt.

2-PHƯƠNG PHÁP 
BƯỚC 1: ĐỌC SÁCH CHỦ ĐỘNG
Khi đọc sách, mình cầm theo một chiếc bút để vừa đọc vừa gạch chân những đoạn quan trọng và viết ra bên lề suy nghĩ, cảm xúc, cảm nhận riêng của mình. Việc làm này kích thích não bộ “tương tác” với sách và tìm ra bài học ứng dụng cho mình.
Bên cạnh đó, mình cũng hay đọc với giấy dán đánh dấu sách (xem video phút thứ 4:20). Với những đoạn tâm đắc muốn đọc lại sau này, mình thường dán giấy đánh dấu ở mặt ngang trang sách. Với những đoạn có bài tập thực hành hay nội dung cần ứng dụng ngay, mình sẽ dán giấy đánh dấu đầu trang sách. Với cách làm này, mình sẽ biết đâu là điều cần làm ngay, và đâu là điều có thể “nhâm nhi” đọc lại sau này.
Nếu bạn không muốn dán giấy nhớ thì cũng có thể gập mép sách lại.
Sau khi đọc xong mỗi chương sách, mình sẽ tóm tắt 3 điều thú vị học được bằng việc viết tóm tắt ở đầu hoặc cuối chương sách. Với những cuốn sách đã có sẵn tóm tắt sách ở cuối chương thì mình sẽ xem lại tóm tắt đó và viết thêm ý riêng của mình.
Mình đã đọc theo quy trình này khoảng 10 năm. Nếu bạn ghé thăm tủ sách nhà mình, mọi cuốn sách từng “qua tay” mình đều có những dấu hiệu ghi chú như vậy.

BƯỚC 2: GHI LẠI “TINH HOA” TRÊN THẺ GHI CHÚ
Sau khi đọc hết một chương hoặc vài chương sách, mình sẽ xem lại những đoạn gạch chân, đánh dấu và chọn xem đâu là những thứ mình thực sự tâm đắc để ghi lại trong bộ thẻ ghi chú của mình (xem video từ phút 5:37).
Mình không ghi thẻ song song với quá trình đọc vì việc này có thể làm gián đoạn sự tập trung khi đọc và mình cần một khoảng thời gian xem lại để thấy đâu mới thực sự là những điều tâm đắc nhất— đâu là “tinh hoa” nhất của cuốn sách để cho vào thẻ của mình. (Ryan Holiday còn ngừng hẳn vài ngày tới vài tuần sau khi đọc hết một cuốn sách để xem lại cả cuốn rồi mới cho vào thẻ)
Đầu tiên, mình ghi vào mép trái thẻ một từ nói lên nội dung chính.
Sau đó mình ghi vào mép phải tên sách viết tắt cùng số trang có thông tin này.
Nội dung dưới mình ghi nhanh lại ý tưởng của sách, câu trích hay trong sách… Nếu có thể, ghi mũi tên rút ra bài học ứng dụng cho riêng mình.
Nếu cần ghi tới mặt sau của thẻ thì mình sẽ đánh dấu mũi tên vòng ra sau (cùng chữ “next”) để nói cho mình biết là thẻ này có hai mặt.
Đôi khi trong quá trình đọc sách, mình liên tưởng nghĩ ra điều gì đó không thực sự được tác giả viết trong sách nhưng liên quan đến vấn đề mình quan tâm thì mình cũng sẽ ghi lại dòng suy nghĩ đó vào thẻ với những tiêu đề cá nhân như “me” cho mình, “content” để làm nội dung, hay “idea” là ý tưởng để xem lại sau này.
Quá trình ghi chú này giúp kiến thức “ăn sâu” vào não hơn nhiều, tăng khả năng ghi nhớ và kích thích ứng dụng cao.

BƯỚC 3: SẮP XẾP THẺ GHI CHÚ
Mình có một chiếc hộp nhỏ riêng để sắp xếp thẻ
Mình sắp xếp thẻ theo thứ tự bảng chữ cái A-Z. Mình dùng bảng chữ cái tiếng Anh vì mình hay đọc sách tiếng Anh nhưng bạn cũng có thể dùng bảng chữ cái tiếng Việt hay ngôn ngữ nào bạn muốn.
Một số đề tài mình có: “leadership” (L), “marketing” (M), “quote” (Q).. được xếp tương ứng theo thứ tự bảng chữ cái
Ngoài ra, mình cũng có một phần những thẻ màu tương ứng với những công việc mình đang làm như: Nghiên cứu (Research), Viết sách (Book), Khóa học (Course), Sản phẩm (Product)… để lưu lại những ý tưởng mình có trong lúc đọc sách liên quan trực tiếp tới những mảng này.

BƯỚC 4: ỨNG DỤNG KIẾN THỨC
Hệ thống chỉ hiệu quả khi người dùng ứng dụng nó. Đối với hệ thống này, bạn có thể thỉnh thoảng mở hộp ra, đọc thẻ để học lại kiến thức theo chu trình học cách quãng (spaced repetition); hoặc khi cần thông tin về một đề tài nhất định, bạn có thể mở ra xem đúng chủ đề đó.
Ví dụ, gần đây mình đang xây dựng Khóa học về lãnh đạo cho học viên chương trình Tiến sĩ và mình lấy được khá nhiều ý tưởng hay ở trong các note về lãnh đạo có trong bộ thẻ của mình.
Như vậy, tùy vào công việc, mục tiêu, và mong muốn cá nhân, bạn có thể cụ thể hóa những cách ứng dụng kiến thức trong thẻ vào hoàn cảnh riêng của mình.

—

Đây là toàn bộ quy trình mình thực hiện đọc, ghi chép theo phương pháp notecard, và ứng dụng vào công việc, cuộc sống. Như đã nói ở ban đầu, mặc dù mới dùng phương này được hai tháng nhưng mình cảm thấy nó đã hoàn toàn thay đổi cuộc đời mình, khiến mọi kiến thức mình học được sinh động hơn, tương tác tốt hơn và nhớ lâu hơn. Mình hy vọng thông tin này cũng giúp được cho bạn—nếu không “thay đổi cuộc đời” thì cũng ít nhất thêm được một phương pháp đọc sách mới vào hệ thống tri thức của mình.
Nếu bạn thấy điều gì đặc biệt giá trị từ bài viết/video này, hãy để lại comment phía dưới chia sẻ với mình và bạn đọc The Present Writer nhé!', 
'uploaded', 1),

(3, 4, 'post3_thumbnail.png', 2, 'Quản lý thời gian hiệu quả hơn với phương pháp Timeboxing! Bạn đã thử?', '2023-05-19', 'Khuất Cường', 'Trong một khảo sát về 100 công cụ hỗ trợ tăng năng suất và quản lý thời gian do Filtered thực hiện, Timeboxing được đánh giá là công cụ hiệu quả nhất.', 
'Trong một khảo sát về 100 công cụ hỗ trợ tăng năng suất và quản lý thời gian do Filtered thực hiện, Timeboxing được đánh giá là công cụ hiệu quả nhất. Vậy thì Timeboxing là gì mà lại được những doanh nhân bận nhất hành tinh như Elon Musk, Bill Gates,… sử dụng trong công việc hàng ngày. Cùng mình tìm hiểu nhé.

Parkinson’s Law
Đầu tiên, để nghiên cứu về Timeboxing, chúng ta sẽ đi qua một “định luật” khá thú vị và là cơ sở cho phương pháp này - Định luật Parkinson.
“Công việc sẽ mở rộng ra để lấp đầy thời gian cho phép hoàn thành nó”
Ví dụ: Bạn đã bao giờ rơi vào trường hợp ôn thi học kỳ và gần như bạn phải ôn tập hoàn toàn kiến thức cả kỳ trong 1-2 buổi tối trước kỳ thi ? Hoặc rơi vào trường hợp giống như mình.
Bỏ qua việc mình quản lý thời gian siêu kém khi phải trải qua những tình huống như trên.
Mình nhận ra: Chúng ta đều có khả năng siêu tập trung (Trong một khoảng thời gian nhất định). Và thời gian đó chúng ta làm việc cực kì hiệu quả.
⇒ Phương pháp Timeboxing ra đời để tối ưu hóa những khoảng thời gian như vậy.

Vậy Timeboxing là gì ?
- Timeboxing theo cách hiểu đơn giản là “đóng gói” công việc của bạn vào trong 1 khoảng thời gian nhất định nào đó.- Hầu hết các công cụ hay phương pháp quản lý thời gian đều tập trung vào việc nhận một công việc và thực hiện nó đến khi nào hoàn thành. Nhưng Timeboxing tập trung vào thời gian và biến nó thành yếu tố thúc đẩy hoàn thành công việc.

Tại sao Timeboxing lại hiệu quả ?
1. Tiêu diệt sự trì hoãn
Timeboxing sẽ yêu cầu đưa ra một giới hạn thời gian cụ thể cho mỗi công việc.
→ Áp đặt giới hạn thời gian giúp tập trung vào nhiệm vụ trước mắt.
→ Giúp cho bản thân bỏ qua những sự mất tập trung và ưu tiên những việc quan trọng.
2. Nói không với đa nhiệm
Do mỗi công việc đều được gắn với các mốc thời gian.
Timeboxing sẽ giúp bạn tránh được hiện tượng “tê liệt quyết định” - một hiện tượng hay xảy ra khi bạn có quá nhiều công việc dồn đến và không biết phải làm gì tiếp theo.
→ Giải quyết công việc nhanh hơn, có thứ tự hơn và không bị cảm giác quá tải.
→ Không còn phải đối mặt với tình trạng làm nhiều thứ một lúc.
Fact: Bộ não của con người cần khoảng 23 phút để có thể hoàn toàn tập trung vào một công việc. Và những công việc khác chỉ có thể bắt đầu hiệu quả sau khi đã xong việc trước đó
3. Dễ dàng đo lường được hiệu quả của bản thân
Một lợi ích khác của Timeboxing là giúp bạn đo lường và ước tính được thời hạn hoàn thành một công việc của bản thân.
Điều này sẽ giúp:
Chọn deadline cho bản thân hợp lý hơnĐo lường được năng suất của bản thân - rất cần thiết khi bạn làm việc từ xa hoặc freelancer
→ Hiểu rõ về thời gian của bản thân - giúp bạn quản lý công việc hiệu quả hơn và tạo ra sự năng suất mong muốn.
“Only what gets measured, gets managed“ - Peter Drucker

Vậy áp dụng Timeboxing như thế nào ?
Bước 1: Liệt kê những công việc cần thiết và ước lượng thời gian hoàn thành nó.
Việc liệt kê hết tất cả những công việc bản thân nghĩ ra sẽ khiến bạn cảm thấy quá tải và hoàn toàn có thể gây ra tác dụng ngược.
→ Cần xem xét và lựa chọn rất kỹ những công việc cần thiết để đưa vào lịch.
Ước lượng sẽ cần nhiều thời gian để có thể thực hiện nó một cách chuẩn xác. Do đó, khi mới bắt đầu, hãy dành cho bản thân những khoảng thời gian chặt chẽ hơn một chút. Và sau đó chúng ta sẽ điều chỉnh dần lại trong quá trình làm việc và tuân thủ Timeboxing.
Bước 2: Xếp những công việc đó vào những mốc thời gian cụ thể
Việc sắp xếp sẽ tùy thuộc vào quan điểm về thời gian và tính chất công việc của mỗi người. Và sắp xếp cũng là một kĩ năng, nên bạn cần thực hiện nó đủ nhiều để tìm ra cách sắp xếp phù hợp nhất với bản thân mình.
Bước 3: Thực hiện và review
Quan trọng nhất chắc chắn là phải tuân thủ theo những mốc thời gian của bản thân. Điều này cần ở bạn một sự kỷ luật và sự tôn trọng với chính bản thân mình.
Việc review cũng rất cần thiết khi nó giúp bạn biết được năng suất của bản thân như thế nào, các mốc thời gian đã hợp lý hay chưa,…
→ Từ đó điều chỉnh - áp dụng Timeboxing sao cho phù hợp và hiệu quả nhất.
Tóm lại là…
Timeboxing là một phương pháp cực kì hiệu quả trong quản lý thời gian - công việc. Tuy nhiên bạn cần ý thức rõ ràng về việc tuân thủ những kế hoạch mà bản thân đặt ra để có thể áp dụng nó một cách tốt nhất.
Chúc các bạn áp dụng thành công phương pháp này để hoàn thành công việc hiệu quả hơn - trong thời gian ngắn hơn, để dành ra được nhiều thời gian cho bản thân hơn bạn nhé!', 
'uploaded', 1);


INSERT INTO `iquiz`.`userprofile`
(`user_profile_id`,
`user_id`,
`avatar`,
`headline`)
VALUES
(1, 1, 'user1avatar.png', 'Passionate Pursuit of Knowledge: A Committed Student Dedicated to Personal Growth'),
(2, 2, 'user2avatar.png', 'Dedicated and Curious: A Passionate Student Committed to Lifelong Learning'),
(3, 3, 'user3avatar.png', 'Excellence in Academics: A Diligent Learner with a Hunger for Knowledge'),
(4, 4, 'user4avatar.png', 'Expert in Mathematics: Pioneering Researcher and Thought Leader in Harvard'),
(5, 5, 'user5avatar.png', 'Acclaimed Expert in English: Shaping the Discourse and Setting the Agenda in Cambridge University'),
(6, 6, 'user6avatar.png', 'Globally Recognized Programming Expert: Driving Change and Making Impact at the Forefront of MIT'),
(7, 7, 'user7avatar.png', 'Customer-Centric Marketer: Understanding and Anticipating Consumer Needs to Drive Engagement and Satisfaction');


INSERT INTO `iquiz`.`usercourse`
(`idCourse`,
`idUser`,
`status`, `date_register` )
VALUES
(3, 2, 'submitted', '2023-05-19' ),
(5, 2, 'completed', '2023-04-19'),
(2, 3, 'completed', '2023-05-10'),
(1, 2, 'completed', '2023-05-12'),
(4, 3, 'submitted', '2023-05-10');

INSERT INTO `iquiz`.`subject_detail`
(`idsub`,
`id_course`)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

INSERT INTO `iquiz`.`subject_overview`
(`idoverview`,
`idsub`,
`status`,
`description`,
`image`)
VALUES
(1, 1, 'published', 'Learn the mathematics needed to become an engineer. Study matrix algebra, differential equations, vector calculus, numerical methods and complete a capstone project.', 'subject1_img.png'),
(2, 2, 'published', 'This course provides an elementary introduction to probability and statistics with applications. Topics include: basic combinatorics, random variables, probability distributions, Bayesian inference, hypothesis testing, confidence intervals, and linear regression.', 'subject2_img.png'),
(3, 3, 'published', 'Improve your fluency, vocabulary, grammar and pronunciation in preparation for the IELTS Speaking test.', 'subject3_img.png'),
(4, 4, 'published', 'Preparation for the Test of English for International Communication. Complete TOEIC TEST preparation course', 'subject4_img.png'),
(5, 5, 'published', 'Become a Full-Stack Web Developer with just ONE course. HTML, CSS, Javascript, Node, React, MongoDB, Web3 and DApps', 'subject5_img.png'),
(6, 6, 'published', 'Become Java Spring Boot Full Stack Cloud Developer. Learn AWS, React, Docker, Spring Data JPA & Spring Security.', 'subject6_img.png');


INSERT INTO `iquiz`.`price_package`
(`idprice`,
`idsub`,
`package_name`,
`duration`,
`price`,
`sale_price`,
`status`)
VALUES
(1, 1, 'Gói truy cập 3 tháng', 3, 500000, 320000, 1),
(2, 1, 'Gói truy cập 6 tháng', 6, 900000, 680000, 1),
(3, 1, 'Gói truy cập vô thời hạn', null, 1500000, 1000000, 0),
(4, 2, 'Gói truy cập 3 tháng', 3, 400000, 300000, 1),
(5, 2, 'Gói truy cập 6 tháng', 6, 700000, 650000, 0),
(6, 2, 'Gói truy cập vô thời hạn', null, 1000000, 800000, 1),
(7, 3, 'Gói truy cập 3 tháng', 3, 700000, 549000, 1),
(8, 3, 'Gói truy cập 6 tháng', 6, 1200000, 999000, 1),
(9, 3, 'Gói truy cập vô thời hạn', null, 1800000, 1000000, 1),
(10, 4, 'Gói truy cập 3 tháng', 3, 500000, 320000, 1),
(11, 4, 'Gói truy cập 6 tháng', 6, 900000, 680000, 1),
(12, 4, 'Gói truy cập vô thời hạn', null, 1500000, 1000000, 1),
(13, 5, 'Gói truy cập 3 tháng', 3, 500000, 320000, 0),
(14, 5, 'Gói truy cập 6 tháng', 6, 900000, 680000, 0),
(15, 5, 'Gói truy cập vô thời hạn', null, 1500000, 1000000, 1),
(16, 6, 'Gói truy cập 3 tháng', 3, 500000, 320000, 1),
(17, 6, 'Gói truy cập 6 tháng', 6, 900000, 680000, 0),
(18, 6, 'Gói truy cập vô thời hạn', null, 1500000, 1000000, 0);

INSERT INTO `iquiz`.`dimension`
(`id`,
`idsub`,
`dimension`,
`type`)
VALUES
(1, 1, 'Business', 'Domain'),
(2, 1, 'Process', 'Domain'),
(3, 1, 'Initializing', 'Group'),
(4, 2, 'Business', 'Domain'),
(5, 2, 'Process', 'Domain'),
(6, 2, 'Initializing', 'Group'),
(7, 3, 'Business', 'Domain'),
(8, 3, 'Process', 'Domain'),
(9, 3, 'Initializing', 'Group'),
(10, 4, 'Business', 'Domain'),
(11, 4, 'Process', 'Domain'),
(12, 4, 'Initializing', 'Group'),
(13, 5, 'Business', 'Domain'),
(14, 5, 'Process', 'Domain'),
(15, 5, 'Initializing', 'Group'),
(16, 6, 'Business', 'Domain'),
(17, 6, 'Process', 'Domain'),
(18, 6, 'Initializing', 'Group');
