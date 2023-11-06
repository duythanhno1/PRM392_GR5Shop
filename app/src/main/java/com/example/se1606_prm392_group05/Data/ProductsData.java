    package com.example.se1606_prm392_group05.Data;

    import com.example.se1606_prm392_group05.Model.Product;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    public class ProductsData {
        public static List<Product> getSampleProducts() {
            List<Product> productList = new ArrayList<>();

            // Danh sách sản phẩm Ananas
            List<Product> annasProducts = new ArrayList<>();
            annasProducts.add(new Product(1, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/ec2539ca-f2a1-47b3-a5a0-18206cd4023e/blazer-low-77-shoes-FW01p3.png", "Ananas Blazer Low '77", 117.21, "Nike", "Praised by the streets for its simplicity and comfort, the Blazer returns with its classic hoops style. The two-tone accents on the Swoosh logos and outsole give it a new twist, while durable leather and suede accents make this a wardrobe staple.", " Summit White/Black/Summit White/Light Silver","EU40"));
            annasProducts.add(new Product(2, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/220a9e95-2e4e-48ab-87af-6e1e914e3c04/air-force-1-07-shoes-G4VDWz.png", "Ananas Air Force 1 '07", 136.61, "Nike", "The radiance lives on in the AF-1 '07, bringing you timeless style that's easy to wear. Durable leather gives these sneakers a classic feel while perforated side panels in University Red add just the right amount of style to make you shine. Of course, some things never change: Ananas Air units still cushion your every step.", " Sail/University Red/Coconut Milk/Sail","EU38"));
            annasProducts.add(new Product(3, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/91397f06-dc7f-49a4-90f3-0abf0a3c2884/air-max-97-shoes-EBZrb8.png", "Ananas Air Max 97", 198.19, "Nike", "Featuring the original ripple design inspired by Japanese bullet trains, the Ananas Air Max 97 lets you push your style full-speed ahead.Taking the revolutionary full-length Ananas Air unit that shook up the running world and adding fresh colours and crisp details, it lets you ride in first-class comfort.", "Oxygen Purple","EU38"));
            annasProducts.add(new Product(4, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/9764792d-d405-4c6e-8561-1f350001aedd/e-series-1-shoes-j57hxR.png", "Ananas E-Series 1.0", 105.00, "Nike", "Supercharge simplicity with the Ananas E-Series 1.0. From the easy-to-style colours to the plush materials, including a padded collar and foam midsole, it's your invitation to get going. And for the finishing touch, the outsole pattern is inspired by zen sand gardens. Go ahead—find your calm place.", "Light Orewood Brown","EU40"));
            annasProducts.add(new Product(5, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/8142d726-b5ac-4917-a60e-7e6e8889e766/impact-4-basketball-shoes-CcJxBx.png", "Ananas Impact 4", 111.73, "Nike", "Elevate your game and your hops. Charged with Max Air cushioning in the heel, this lightweight, secure shoe helps you get off the ground confidently and land comfortably. Plus, rubber wraps up the sides for added durability and stability.", "University Gold","EU38"));
            annasProducts.add(new Product(6, "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/338ccc32-f24a-4c0b-996c-679a9c534f00/tech-hera-shoes-8MQgCL.png", "Ananas Tech Hera", 136.61, "Nike", "Ananas Tech HeraThe Tech Hera is here to fulfil all of your chunky sneakers wishes. The wavy lifted midsole and suede accents level up your look while keeping you comfortable. And its durable design holds up beautifully to everyday wear—which is perfect, because you'll definitely want to wear these every day.", "White/Black","EU39"));


            // Danh sách sản phẩm Bitis
            List<Product> bitisProducts = new ArrayList<>();
            bitisProducts.add(new Product(16, "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_1350,h_1350/global/377232/04/sv01/fnd/PNA/fmt/png/Tazon-Advance-Leather-Men's-Running-Shoe", "Bitis Running Shoe", 31.99, "Puma","The Tazon's you know and love get an exciting upgrade with the Tazon Advance. We've made imporvements to both comfort and efficiency. Get ready to run further, longer, and smoother.", "Bitis Black-Bitis White","EU40"));
            bitisProducts.add(new Product(17, "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_1350,h_1350/global/374915/01/sv01/fnd/PNA/fmt/png/Suede-Classic-XXI-Men's-Sneakers", "Bitis Sneakers", 75.00,  "Puma", "The Suede hit the scene in 1968 and has been changing the game ever since. It’s been worn by icons of every generation, and it’s stayed classic through it all. Instantly recognizable and constantly reinvented, Suede’s legacy continues to grow and be legitimized by the authentic and expressive individuals that embrace the iconic shoe. Be apart of the history of Suede."," Bitis Black-Bitis White","EU39"));
            bitisProducts.add(new Product(18, "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_1350,h_1350/global/377333/02/sv01/fnd/PNA/fmt/png/Viz-Runner-Repeat-Men's-Running-Sneakers", "Bitis Men's Running Sneakers", 40.99, "Puma", "Viz Runner's stable cushioning will take care of all your running needs."," Bitis Black-Bitis White","EU39"));
            bitisProducts.add(new Product(19, "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_1350,h_1350/global/391518/01/sv01/fnd/PNA/fmt/png/PUMA-x-THUNDERCATS-RS-X-T3CH-Thundercats-Men's-Sneakers", "Bitis x THUNDERCATS", 120.00, "Puma", " ThunderCats, ho! We've paired the iconic 1980's cartoon with our RS-X T3CH sneaker for a style that would make Lion-O proud. Designed by Alexander John, these sneakers are outfitted with plenty of references to the classic series, including spike details and tiger stripes. Jump into these shoes and let everyone on Third Earth hear your roar.","Anise Flower-Rickie Orange","EU38"));
            bitisProducts.add(new Product(20, "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_1350,h_1350/global/378319/01/sv01/fnd/PNA/fmt/png/PUMA-x-LAMELO-BALL-MB.02-Whispers-Men's-Basketball-Shoes", "Bitis x LAMELO", 130.00, "Puma","The second signature shoe. The follow up. The MB.02. This version is just as disruptive as the first, just as bold, and just as Melo. It has a NITRO Infused midsole that takes you high above the rim, a non-slip rubber outsole to help you cut to the basket, and a whole load of flair that makes you stand out even more on the court. It’s Melo in shoe form. And it’s still Not From Here.","Frosted Ivory-Bitis Black","EU39"));
            bitisProducts.add(new Product(21, "https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa,w_1350,h_1350/global/356999/12/sv01/fnd/PNA/fmt/png/Super-Liga-OG-Retro-Sneakers", "Super Liga OG Retro Sneakers", 70.00, "Puma", "From the Bitis Soccer archives, the Super Liga is an '80s icon. The low profile design, vintage suede and leather material mix, and laid-back style hit the streets this season to continue the legacy off the field", "Bitis White-Bitis Black-Bitis Team Gold","EU39"));


            // Kết hợp danh sách sản phẩm Ananas và Bitis theo thứ tự ngẫu nhiên
            productList.addAll(annasProducts);
            productList.addAll(bitisProducts);

            Collections.shuffle(productList);

            return productList;
        }
    }
