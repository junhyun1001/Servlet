<%@ page import="org.example.mvc.model.entity.Pet" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>반려동물 관리 서비스</title>
    <style>
        body {
            font-family: 'Google Sans', sans-serif, system-ui;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            color: #3c4043;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
        }
        h1, h3 {
            color: #1a73e8;
        }
        .form-container, .pet-list-container {
            background-color: #ffffff;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
            margin-bottom: 30px;
        }
        form .form-group {
            margin-bottom: 15px;
        }
        form label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        form input[type="text"],
        form input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #dadce0;
            border-radius: 8px;
            font-size: 16px;
        }
        form button {
            background-color: #1a73e8;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        form button:hover {
            background-color: #174ea6;
        }
        .pet-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }
        .pet-card {
            background-color: #f1f3f4;
            border-radius: 12px;
            padding: 20px;
            transition: all 0.3s cubic-bezier(.25,.8,.25,1);
        }
        .pet-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .pet-card p {
            margin: 5px 0;
            line-height: 1.5;
        }
        .pet-card b {
            color: #1a73e8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🐾 반려동물 관리 서비스</h1>

        <div class="form-container">
            <h3>새로운 반려동물 등록</h3>
            <form method="post">
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" id="name" name="name" placeholder="예: 누렁이" required>
                </div>
                <div class="form-group">
                    <label for="age">나이</label>
                    <input type="number" id="age" name="age" placeholder="예: 5" required>
                </div>
                <div class="form-group">
                    <label for="category">종</label>
                    <input type="text" id="category" name="category" placeholder="예: 강아지" required>
                </div>
                <button type="submit">등록하기</button>
            </form>
        </div>

        <div class="pet-list-container">
            <h3>등록된 반려동물 목록</h3>
            <div class="pet-grid">
                <% 
                    Object petListAttr = request.getAttribute("petList");
                    if (petListAttr instanceof List) {
                        List<Pet> petList = (List<Pet>) petListAttr;
                        if (!petList.isEmpty()) {
                            for (Pet pet : petList) {
                %>
                <div class="pet-card">
                    <p><b>이름:</b> <%= pet.name() %></p>
                    <p><b>나이:</b> <%= pet.age() %></p>
                    <p><b>종:</b> <%= pet.category() %></p>
                </div>
                <% 
                            }
                        } else {
                %>
                <p>등록된 반려동물이 없습니다. 위 폼을 통해 추가해주세요.</p>
                <% 
                        }
                    } else {
                %>
                <p>반려동물 목록을 불러올 수 없습니다.</p>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
