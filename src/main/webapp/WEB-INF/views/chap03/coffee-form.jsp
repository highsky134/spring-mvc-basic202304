<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        label {
            display: block;
            margin-bottom: 20px;
        }
        .wrap {
            width: 800px;
            margin: 100px auto;
            border: 1px dashed #000;
        }
        .wrap h1 {
            margin: 0 auto;
            padding-bottom: 20px;
            width: fit-content;
            border-bottom: 1px solid #000;
        }
        .wrap .menu {
            font-size: 24px;
            width: 80%;
            margin: 20px auto;
        }
        .wrap .menu select {
            width: 200px;
            height: 50px;
            font-size: 28px;
            margin-top: 10px;
        }
        .clearfix::after {
            content: '';
            display: block;
            clear: both;
        }
    </style>
</head>
<body>
    
    <div class="wrap">
        <h1>커피 주문서</h1>

        <div class="menu">
            <form action="/coffee/result" method="post">
                <label>
                    # 주문 목록 <br>
                    <select id="menu-sel" name="menu">
                        <option value="americano">아메리카노</option>
                        <option value="cafeLatte">카페라떼</option>
                        <option value="macchiato">카라멜 마끼아또</option>                        
                    </select>
                </label>
                <label class="price"># 가격: <span class="price-value">3000</span>원</label>
                <!-- hidden 화면에 렌더링은 안되지만 서버로 보낼 수 있음 -->
                <input id="price-tag" type="hidden" name="price" value="3000">
                <input id="sale-point" type="hidden" name="sale" value="1">

                <label>
                    <button id="btn" type="submit">주문하기</button>
                </label>
            </form>
        </div>
    </div>

    <script>

        const coffeePrice = {
            americano: 3000,
            cafeLatte: 4500,
            macchiato: 5000
        }

        // change : input, select 등의 태그의 값이 변했을 때
        const $menu = document.getElementById("menu-sel");
        $menu.onchange = e => {
            // 커피를 선택하면 가격이 변해야 함!
            console.log(e.target);
            console.log(coffeePrice[e.target.value]);
            let price = document.querySelector(".price-value");
            price.textContent = coffeePrice[e.target.value];
            // location.href = "/coffee/choice";
            const $priceTag = document.getElementById("price-tag");
            $priceTag.value = coffeePrice[e.target.value];
        };

        const $btn = document.getElementById("btn");
        const $sale = document.getElementById("sale-point");
        $btn.onmousedown = e => {            
            const rd = Math.floor(Math.random() * 5 + 1) * 5;
            $sale.value = rd;            
        }


    </script>


</body>
</html>