<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.News" %> 
<!DOCTYPE html>
<html lang="en"> 
<!--    <head>ManageNews-->
        <title>Portal - Bootstrap 5 Admin Dashboard Template For Developers</title>

        <!-- Meta -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Portal - Bootstrap 5 Admin Dashboard Template For Developers">
        <meta name="author" content="Xiaoying Riley at 3rd Wave Media">    
        <link rel="shortcut icon" href="../../favicon.ico"> 

        <!-- FontAwesome JS -->
        <script defer src="../../assets/plugins/fontawesome/js/all.min.js"></script>

        <!-- App CSS -->  
        <link id="theme-style" rel="stylesheet" href="assets/css/portal.css">
        <style>
            .d-flex {
                display: flex;
                justify-content: center; /* Căn giữa theo chiều ngang */
                align-items: center; /* Căn giữa theo chiều dọc (nếu cần) */
                height: 100vh; /* Đặt chiều cao của div cha nếu cần */
            }
            .app-pagination {
    position: fixed;
    bottom: 0;
    left: 30px;
    width: 100%; /* Chiều rộng đầy đủ */
    background-color: white; /* Đặt nền tùy ý */
    text-align: center; /* Căn giữa nội dung */
    padding: 10px 0; /* Khoảng cách trên và dưới */
    box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1); /* Tạo bóng nếu cần */
    z-index: 1000; /* Đảm bảo nó nằm trên các phần tử khác */
}

        </style>
    </head> 


    <body class="app">   	
        <header class="app-header fixed-top">	   	            
            <div class="app-header-inner">  
                <div class="container-fluid py-2">
                    <div class="app-header-content"> 
                        <div class="row justify-content-between align-items-center">

                            <div class="col-auto">
                                <a id="sidepanel-toggler" class="sidepanel-toggler d-inline-block d-xl-none" href="#">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 30 30" role="img"><title>Menu</title><path stroke="currentColor" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2" d="M4 7h22M4 15h22M4 23h22"></path></svg>
                                </a>
                            </div><!--//col-->
                            <div class="search-mobile-trigger d-sm-none col">
                                <i class="search-mobile-trigger-icon fa-solid fa-magnifying-glass"></i>
                            </div><!--//col-->
                            <div class="app-search-box col">
                                <form class="app-search-form" method="get" action="your_servlet_url_here">
                                    <input type="text" placeholder="Search..." name="keyW" value="${param.search}" class="form-control search-input">
                                    <!-- Khi người dùng tìm kiếm, trang mặc định sẽ là 1 -->
                                    <input type="hidden" name="page" value="1">
                                    <button type="submit" class="btn search-btn btn-primary" value="Search">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </button></form>
                            </div><!--//app-search-box-->

                            <div class="app-utilities col-auto">
                                <div class="app-utility-item app-notifications-dropdown dropdown">    
                                    <a class="dropdown-toggle no-toggle-arrow" id="notifications-dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false" title="Notifications">
                                        <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bell icon" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2z"/>
                                        <path fill-rule="evenodd" d="M8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
                                        </svg>
                                        <span class="icon-badge">3</span>
                                    </a><!--//dropdown-toggle-->

                                    <div class="dropdown-menu p-0" aria-labelledby="notifications-dropdown-toggle">
                                        <div class="dropdown-menu-header p-3">
                                            <h5 class="dropdown-menu-title mb-0">Notifications</h5>
                                        </div><!--//dropdown-menu-title-->
                                        <div class="dropdown-menu-content">
                                            <div class="item p-3">
                                                <div class="row gx-2 justify-content-between align-items-center">
                                                    <div class="col-auto">
                                                        <img class="profile-image" src="../../assets/images/profiles/profile-1.png" alt="">
                                                    </div><!--//col-->
                                                    <div class="col">
                                                        <div class="info"> 
                                                            <div class="desc">Amy shared a file with you. Lorem ipsum dolor sit amet, consectetur adipiscing elit. </div>
                                                            <div class="meta"> 2 hrs ago</div>
                                                        </div>
                                                    </div><!--//col--> 
                                                </div><!--//row-->
                                                <a class="link-mask" href="notifications.html"></a>
                                            </div><!--//item-->
                                            <div class="item p-3">
                                                <div class="row gx-2 justify-content-between align-items-center">
                                                    <div class="col-auto">
                                                        <div class="app-icon-holder">
                                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-receipt" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                            <path fill-rule="evenodd" d="M1.92.506a.5.5 0 0 1 .434.14L3 1.293l.646-.647a.5.5 0 0 1 .708 0L5 1.293l.646-.647a.5.5 0 0 1 .708 0L7 1.293l.646-.647a.5.5 0 0 1 .708 0L9 1.293l.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .801.13l.5 1A.5.5 0 0 1 15 2v12a.5.5 0 0 1-.053.224l-.5 1a.5.5 0 0 1-.8.13L13 14.707l-.646.647a.5.5 0 0 1-.708 0L11 14.707l-.646.647a.5.5 0 0 1-.708 0L9 14.707l-.646.647a.5.5 0 0 1-.708 0L7 14.707l-.646.647a.5.5 0 0 1-.708 0L5 14.707l-.646.647a.5.5 0 0 1-.708 0L3 14.707l-.646.647a.5.5 0 0 1-.801-.13l-.5-1A.5.5 0 0 1 1 14V2a.5.5 0 0 1 .053-.224l.5-1a.5.5 0 0 1 .367-.27zm.217 1.338L2 2.118v11.764l.137.274.51-.51a.5.5 0 0 1 .707 0l.646.647.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.646.646.646-.646a.5.5 0 0 1 .708 0l.509.509.137-.274V2.118l-.137-.274-.51.51a.5.5 0 0 1-.707 0L12 1.707l-.646.647a.5.5 0 0 1-.708 0L10 1.707l-.646.647a.5.5 0 0 1-.708 0L8 1.707l-.646.647a.5.5 0 0 1-.708 0L6 1.707l-.646.647a.5.5 0 0 1-.708 0L4 1.707l-.646.647a.5.5 0 0 1-.708 0l-.509-.51z"/>
                                                            <path fill-rule="evenodd" d="M3 4.5a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 1 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5zm8-6a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5z"/>
                                                            </svg>
                                                        </div>
                                                    </div><!--//col-->
                                                    <div class="col">
                                                        <div class="info"> 
                                                            <div class="desc">You have a new invoice. Proin venenatis interdum est.</div>
                                                            <div class="meta"> 1 day ago</div>
                                                        </div>
                                                    </div><!--//col-->
                                                </div><!--//row-->
                                                <a class="link-mask" href="notifications.html"></a>
                                            </div><!--//item-->
                                            <div class="item p-3">
                                                <div class="row gx-2 justify-content-between align-items-center">
                                                    <div class="col-auto">
                                                        <div class="app-icon-holder icon-holder-mono">
                                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bar-chart-line" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                            <path fill-rule="evenodd" d="M11 2a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1v-3a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3h1V7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7h1V2zm1 12h2V2h-2v12zm-3 0V7H7v7h2zm-5 0v-3H2v3h2z"/>
                                                            </svg>
                                                        </div>
                                                    </div><!--//col-->
                                                    <div class="col">
                                                        <div class="info"> 
                                                            <div class="desc">Your report is ready. Proin venenatis interdum est.</div>
                                                            <div class="meta"> 3 days ago</div>
                                                        </div>
                                                    </div><!--//col-->
                                                </div><!--//row-->
                                                <a class="link-mask" href="notifications.html"></a>
                                            </div><!--//item-->
                                            <div class="item p-3">
                                                <div class="row gx-2 justify-content-between align-items-center">
                                                    <div class="col-auto">
                                                        <img class="profile-image" src="../../assets/images/profiles/profile-2.png" alt="">
                                                    </div><!--//col-->
                                                    <div class="col">
                                                        <div class="info"> 
                                                            <div class="desc">James sent you a new message.</div>
                                                            <div class="meta"> 7 days ago</div>
                                                        </div>
                                                    </div><!--//col--> 
                                                </div><!--//row-->
                                                <a class="link-mask" href="notifications.html"></a>
                                            </div><!--//item-->
                                        </div><!--//dropdown-menu-content-->

                                        <div class="dropdown-menu-footer p-2 text-center">
                                            <a href="notifications.html">View all</a>
                                        </div>

                                    </div><!--//dropdown-menu-->					        
                                </div><!--//app-utility-item-->
                                <div class="app-utility-item">
                                    <a href="settings.html" title="Settings">
                                        <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-gear icon" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M8.837 1.626c-.246-.835-1.428-.835-1.674 0l-.094.319A1.873 1.873 0 0 1 4.377 3.06l-.292-.16c-.764-.415-1.6.42-1.184 1.185l.159.292a1.873 1.873 0 0 1-1.115 2.692l-.319.094c-.835.246-.835 1.428 0 1.674l.319.094a1.873 1.873 0 0 1 1.115 2.693l-.16.291c-.415.764.42 1.6 1.185 1.184l.292-.159a1.873 1.873 0 0 1 2.692 1.116l.094.318c.246.835 1.428.835 1.674 0l.094-.319a1.873 1.873 0 0 1 2.693-1.115l.291.16c.764.415 1.6-.42 1.184-1.185l-.159-.291a1.873 1.873 0 0 1 1.116-2.693l.318-.094c.835-.246.835-1.428 0-1.674l-.319-.094a1.873 1.873 0 0 1-1.115-2.692l.16-.292c.415-.764-.42-1.6-1.185-1.184l-.291.159A1.873 1.873 0 0 1 8.93 1.945l-.094-.319zm-2.633-.283c.527-1.79 3.065-1.79 3.592 0l.094.319a.873.873 0 0 0 1.255.52l.292-.16c1.64-.892 3.434.901 2.54 2.541l-.159.292a.873.873 0 0 0 .52 1.255l.319.094c1.79.527 1.79 3.065 0 3.592l-.319.094a.873.873 0 0 0-.52 1.255l.16.292c.893 1.64-.902 3.434-2.541 2.54l-.292-.159a.873.873 0 0 0-1.255.52l-.094.319c-.527 1.79-3.065 1.79-3.592 0l-.094-.319a.873.873 0 0 0-1.255-.52l-.292.16c-1.64.893-3.433-.902-2.54-2.541l.159-.292a.873.873 0 0 0-.52-1.255l-.319-.094c-1.79-.527-1.79-3.065 0-3.592l.319-.094a.873.873 0 0 0 .52-1.255l-.16-.292c-.892-1.64.902-3.433 2.541-2.54l.292.159a.873.873 0 0 0 1.255-.52l.094-.319z"/>
                                        <path fill-rule="evenodd" d="M8 5.754a2.246 2.246 0 1 0 0 4.492 2.246 2.246 0 0 0 0-4.492zM4.754 8a3.246 3.246 0 1 1 6.492 0 3.246 3.246 0 0 1-6.492 0z"/>
                                        </svg>
                                    </a>
                                </div><!--//app-utility-item-->

                                <div class="app-utility-item app-user-dropdown dropdown">
                                    <a class="dropdown-toggle" id="user-dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false"><img src="../../assets/images/user.png" alt="user profile"></a>
                                    <ul class="dropdown-menu" aria-labelledby="user-dropdown-toggle">
                                        <li><a class="dropdown-item" href="account.html">Account</a></li>
                                        <li><a class="dropdown-item" href="settings.html">Settings</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="logout">Log Out</a></li>
                                    </ul>
                                </div><!--//app-user-dropdown--> 
                            </div><!--//app-utilities-->
                        </div><!--//row-->
                    </div><!--//app-header-content-->
                </div><!--//container-fluid-->
            </div><!--//app-header-inner-->
             <div id="app-sidepanel" class="app-sidepanel sidepanel-hidden"> 
                <div id="sidepanel-drop" class="sidepanel-drop"></div>
                <div class="sidepanel-inner d-flex flex-column">
                    <a href="#" id="sidepanel-close" class="sidepanel-close d-xl-none">&times;</a>
                    <div class="app-branding">
                        <a class="app-logo" href="index.html"><span class="logo-text">ADMIN PAGE</span></a>

                    </div><!--//app-branding-->  
                    <nav id="app-nav-main" class="app-nav app-nav-main flex-grow-1">
                        <ul class="app-menu list-unstyled accordion" id="menu-accordion">
                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link" href="index.html">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-door" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M7.646 1.146a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5H9.5a.5.5 0 0 1-.5-.5v-4H7v4a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6zM2.5 7.707V14H6v-4a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4h3.5V7.707L8 2.207l-5.5 5.5z"/>
                                        <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">Overview</span>
                                </a><!--//nav-link-->
                            </li><!--//nav-item-->

                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link " href="AccoutListController">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-card-list" fill="currentColor" xmlns="http://www.w3.org/2000/svg">

                                        <path d="M9.828 4a3 3 0 0 1-2.12-.879l-.83-.828A1 1 0 0 0 6.173 2H2.5a1 1 0 0 0-1 .981L1.546 4h-1L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3v1z"/>
                                        <path fill-rule="evenodd" d="M13.81 4H2.19a1 1 0 0 0-.996 1.09l.637 7a1 1 0 0 0 .995.91h10.348a1 1 0 0 0 .995-.91l.637-7A1 1 0 0 0 13.81 4zM2.19 3A2 2 0 0 0 .198 5.181l.637 7A2 2 0 0 0 2.826 14h10.348a2 2 0 0 0 1.991-1.819l.637-7A2 2 0 0 0 13.81 3H2.19z"/>


                                        <circle cx="3.5" cy="5.5" r=".5"/>
                                        <circle cx="3.5" cy="8" r=".5"/>
                                        <circle cx="3.5" cy="10.5" r=".5"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">List Account</span>
                                </a><!--//nav-link-->
                            </li><!--//nav-item-->

                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link" href="ProductListController">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-folder" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
                                        <path fill-rule="evenodd" d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5z"/>

                                        <circle cx="3.5" cy="5.5" r=".5"/>
                                        <circle cx="3.5" cy="8" r=".5"/>
                                        <circle cx="3.5" cy="10.5" r=".5"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">List Product</span>
                                </a><!--//nav-link-->
                            </li><!--//nav-item-->

                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link" href="BrandController">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-folder" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M9.828 4a3 3 0 0 1-2.12-.879l-.83-.828A1 1 0 0 0 6.173 2H2.5a1 1 0 0 0-1 .981L1.546 4h-1L.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3v1z"/>
                                        <path fill-rule="evenodd" d="M13.81 4H2.19a1 1 0 0 0-.996 1.09l.637 7a1 1 0 0 0 .995.91h10.348a1 1 0 0 0 .995-.91l.637-7A1 1 0 0 0 13.81 4zM2.19 3A2 2 0 0 0 .198 5.181l.637 7A2 2 0 0 0 2.826 14h10.348a2 2 0 0 0 1.991-1.819l.637-7A2 2 0 0 0 13.81 3H2.19z"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">List Brand</span>
                                </a><!--//nav-link-->
                            </li><!--//nav-item-->
                           
                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link active " href="ManageNews">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-door" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M7.646 1.146a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5H9.5a.5.5 0 0 1-.5-.5v-4H7v4a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6zM2.5 7.707V14H6v-4a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4h3.5V7.707L8 2.207l-5.5 5.5z"/>
                                        <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">List News</span>
                                </a><!--//nav-link-->
                            </li>
                            <li class="nav-item has-submenu">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link submenu-toggle" href="#" data-bs-toggle="collapse" data-bs-target="#submenu-2" aria-expanded="false" aria-controls="submenu-2">
                                    <span class="nav-icon">
                                        <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-columns-gap" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M6 1H1v3h5V1zM1 0a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h5a1 1 0 0 0 1-1V1a1 1 0 0 0-1-1H1zm14 12h-5v3h5v-3zm-5-1a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h5a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-5zM6 8H1v7h5V8zM1 7a1 1 0 0 0-1 1v7a1 1 0 0 0 1 1h5a1 1 0 0 0 1-1V8a1 1 0 0 0-1-1H1zm14-6h-5v7h5V1zm-5-1a1 1 0 0 0-1 1v7a1 1 0 0 0 1 1h5a1 1 0 0 0 1-1V1a1 1 0 0 0-1-1h-5z"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">Statistics</span>
                                    <span class="submenu-arrow">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-down" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                    </span><!--//submenu-arrow-->
                                </a><!--//nav-link-->
                                <div id="submenu-2" class="collapse submenu submenu-2" data-bs-parent="#menu-accordion">
                                    <ul class="submenu-list list-unstyled">
                                        <li class="submenu-item"><a class="submenu-link" href="MonthRevenue">Month revenue</a></li>
                                        <li class="submenu-item"><a class="submenu-link" href="WeekRevenue">Week revenue</a></li>
                                        <li class="submenu-item"><a class="submenu-link" href="MonthQuantity">Month quantity</a></li>
                                        <li class="submenu-item"><a class="submenu-link" href="WeekQuantity">Week quantity</a></li>
                                    </ul>
                                </div>
                            </li><!--//nav-item-->
                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link" href="Top10Products">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bar-chart-line" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                           <path fill-rule="evenodd" d="M11 2a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1v-3a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3h1V7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7h1V2zm1 12h2V2h-2v12zm-3 0V7H7v7h2zm-5 0v-3H2v3h2z"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">Top 10 Products</span>
                                </a><!--//nav-link-->
                            </li><!--//nav-item-->
                            <li class="nav-item">
                                <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                <a class="nav-link" href="help.html">
                                    <span class="nav-icon">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-question-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"/>
                                        </svg>
                                    </span>
                                    <span class="nav-link-text">Help</span>
                                </a><!--//nav-link-->
                            </li><!--//nav-item-->
                        </ul><!--//app-menu-->
                    </nav><!--//app-nav-->
                    <div class="app-sidepanel-footer">
                        <nav class="app-nav app-nav-footer">
                            <ul class="app-menu footer-menu list-unstyled">
                                <li class="nav-item">
                                    <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                    <a class="nav-link" href="settings.html">
                                        <span class="nav-icon">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-gear" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M8.837 1.626c-.246-.835-1.428-.835-1.674 0l-.094.319A1.873 1.873 0 0 1 4.377 3.06l-.292-.16c-.764-.415-1.6.42-1.184 1.185l.159.292a1.873 1.873 0 0 1-1.115 2.692l-.319.094c-.835.246-.835 1.428 0 1.674l.319.094a1.873 1.873 0 0 1 1.115 2.693l-.16.291c-.415.764.42 1.6 1.185 1.184l.292-.159a1.873 1.873 0 0 1 2.692 1.116l.094.318c.246.835 1.428.835 1.674 0l.094-.319a1.873 1.873 0 0 1 2.693-1.115l.291.16c.764.415 1.6-.42 1.184-1.185l-.159-.291a1.873 1.873 0 0 1 1.116-2.693l.318-.094c.835-.246.835-1.428 0-1.674l-.319-.094a1.873 1.873 0 0 1-1.115-2.692l.16-.292c.415-.764-.42-1.6-1.185-1.184l-.291.159A1.873 1.873 0 0 1 8.93 1.945l-.094-.319zm-2.633-.283c.527-1.79 3.065-1.79 3.592 0l.094.319a.873.873 0 0 0 1.255.52l.292-.16c1.64-.892 3.434.901 2.54 2.541l-.159.292a.873.873 0 0 0 .52 1.255l.319.094c1.79.527 1.79 3.065 0 3.592l-.319.094a.873.873 0 0 0-.52 1.255l.16.292c.893 1.64-.902 3.434-2.541 2.54l-.292-.159a.873.873 0 0 0-1.255.52l-.094.319c-.527 1.79-3.065 1.79-3.592 0l-.094-.319a.873.873 0 0 0-1.255-.52l-.292.16c-1.64.893-3.433-.902-2.54-2.541l.159-.292a.873.873 0 0 0-.52-1.255l-.319-.094c-1.79-.527-1.79-3.065 0-3.592l.319-.094a.873.873 0 0 0 .52-1.255l-.16-.292c-.892-1.64.902-3.433 2.541-2.54l.292.159a.873.873 0 0 0 1.255-.52l.094-.319z"/>
                                            <path fill-rule="evenodd" d="M8 5.754a2.246 2.246 0 1 0 0 4.492 2.246 2.246 0 0 0 0-4.492zM4.754 8a3.246 3.246 0 1 1 6.492 0 3.246 3.246 0 0 1-6.492 0z"/>
                                            </svg>
                                        </span>
                                        <span class="nav-link-text">Settings</span>
                                    </a><!--//nav-link-->
                                </li><!--//nav-item-->
                                <li class="nav-item">
                                    <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                    <a class="nav-link" href="https://themes.3rdwavemedia.com/bootstrap-templates/admin-dashboard/portal-free-bootstrap-admin-dashboard-template-for-developers/">
                                        <span class="nav-icon">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-download" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
                                            <path fill-rule="evenodd" d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
                                            </svg>
                                        </span>
                                        <span class="nav-link-text">Download</span>
                                    </a><!--//nav-link-->
                                </li><!--//nav-item-->
                                <li class="nav-item">
                                    <!--//Bootstrap Icons: https://icons.getbootstrap.com/ -->
                                    <a class="nav-link" href="https://themes.3rdwavemedia.com/bootstrap-templates/admin-dashboard/portal-free-bootstrap-admin-dashboard-template-for-developers/">
                                        <span class="nav-icon">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-person" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12 1H4a1 1 0 0 0-1 1v10.755S4 11 8 11s5 1.755 5 1.755V2a1 1 0 0 0-1-1zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z"/>
                                            <path fill-rule="evenodd" d="M8 10a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                                            </svg>
                                        </span>
                                        <span class="nav-link-text">License</span>
                                    </a><!--//nav-link-->
                                </li><!--//nav-item-->
                            </ul><!--//footer-menu-->
                        </nav>
                    </div><!--//app-sidepanel-footer-->
                </div><!--//sidepanel-inner-->
            </div><!--//app-sidepanel-->
        </header><!--//app-header-->

        <div class="app-wrapper">

            <div class="app-content pt-3 p-md-3 p-lg-4">
                <div class="container-xl">

                    <div class="row g-3 mb-4 align-items-center justify-content-between">
                        <div class="col-auto">
                            <h1 class="app-page-title mb-0">Manage News</h1>
                        </div>
                        <div class="col-auto">
                            <div class="page-utilities">
                                <div class="row g-2 justify-content-start justify-content-md-end align-items-center">
                                    <div class="col-auto" style="margin: 0 auto;display: flex;margin-right: 50px;">
                                        <form class="table-search-form row gx-1 align-items-center" method="get" action="ManageNews" style="margin: 0 auto;">
                                            <div class="col-auto">
                                                <input type="text" placeholder="Search..." name="keyW" value="${param.keyW}" class="form-control search-orders">
                                            </div>
                                            <div class="col-auto">
                                                
                                            </div>
                                            <input type="hidden" name="page" value="1">		
                                            <div class="col-auto">
                                                <button type="submit" class="btn search-btn btn-primary" value="Search">
                                                    Submit <i class="fa-solid fa-magnifying-glass"></i>
                                                </button>					
                                            </div>
                                        </form>

                                    </div><!--//col-->
                                    <!--//col-->


                                    <div class="col-auto">						    
                                        <a class="btn app-btn-secondary" href="AddNews">

                                            Add New
                                        </a>
                                    </div>
                                </div><!--//row-->
                            </div><!--//table-utilities-->
                        </div><!--//col-auto-->
                    </div><!--//row-->


                   <c:if test="${not empty message}">
                        <div class="alert alert-success" role="alert">
                            ${message}
                        </div>
                    </c:if>


                    <div class="tab-content" id="orders-table-tab-content">
                        <div class="tab-pane fade show active" id="orders-all" role="tabpanel" aria-labelledby="orders-all-tab">
                            <div class="app-card app-card-orders-table shadow-sm mb-5">
                                <div class="app-card-body">
                                    <div class="table-responsive">
                                        <table class="table app-table-hover mb-0 text-left">
                                            <thead>
                                                <tr>
                                                    <th class="cell">NewsID</th>
                                                    <th class="cell">Title</th>
                                                    <th class="cell">Date Upload</th>
                                                    <th class="cell">Author</th>
                                                    <th class="cell">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- Kiểm tra xem danh sách tài khoản có trống không -->
                                                <c:if test="${not empty newsList}">
                                                    <c:forEach var="newsList" items="${newsList}">
                                                        <tr>
                                                            <td>${newsList.newsID}</td>
                                                            <td><span class="truncate">${newsList.title}</span></td>
                                                            <td>${newsList.uploadDate}</td>
                                                            <td>${newsList.author}</td> 
                                                              <td> 
                                                                  <button>  <a class="btn-sm app-btn-danger" href="DeletesNews?id=${newsList.newsID}" onclick="return confirm('Are you sure you want to delete this News?');">Delete</a></button>
                                                                    <button>  <a class="btn-sm app-btn-danger" href="EditNewsController?id=${newsList.newsID}" >Edit</a></button>

                                                              </td> 
                                                        
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <!-- Nếu danh sách trống, hiển thị thông báo -->
                                                <c:if test="${empty newsList}">
                                                    <tr>
                                                        <td colspan="7" class="cell">No news found.</td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>

                                    </div><!--//table-responsive-->

                                </div><!--//app-card-body-->		
                            </div><!--//app-card-->
                            <nav class="app-pagination">
                                <ul class="pagination justify-content-center">
                                    <!-- Nút Previous -->
                                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="?page=${currentPage - 1}" tabindex="-1" aria-disabled="true">Previous</a>
                                    </li>

                                    <!-- Lặp qua các số trang -->
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                            <a class="page-link" href="?keyW=${param.keyW}&page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <!-- Nút Next -->
                                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                        <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                                    </li>
                                </ul>
                            </nav>
                            <!--//app-pagination-->

                        </div><!--//tab-pane-->


                        
                    </div><!--//tab-content-->



                </div><!--//container-fluid-->
            </div><!--//app-content-->

         

        </div><!--//app-wrapper-->    					


        <!-- Javascript -->          
        <script src="assets/plugins/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>  
        <script src="assets/js/app.js"></script> 

    </body>
</html> 

