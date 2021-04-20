<?php require APPROOT . '/views/inc/header_admin.php'; ?>
<div class="container m-0 p-0">
    <div class="row">
        <div class="col-3">
            <div class="d-flex flex-column p-3 text-white bg-dark" style="height: 100vh; width: 280px;">
            <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
                <span class="fs-4">Sidebar</span>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li class="nav-item">
                <a href="#" class="nav-link active">
                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
                    Home
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white">
                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
                    Dashboard
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white">
                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
                    Orders
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white">
                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
                    Products
                </a>
                </li>
                <li>
                <a href="#" class="nav-link text-white">
                    <svg class="bi me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
                    Customers
                </a>
                </li>
            </ul>
            <hr>
        </div>
    </div>
    <div class="col-9 p-5">
        <div class="container-fluid border border-2 bg-white shadow w-100 h-100 rounded-3" style="border-radius: 20px;">
            <p>Hello hello hello</p>
        </div>
    </div>
</div>
  <!-- <div class="dropdown">
    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
      <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle me-2">
      <strong>mdo</strong>
    </a>
    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
      <li><a class="dropdown-item" href="#">New project...</a></li>
      <li><a class="dropdown-item" href="#">Settings</a></li>
      <li><a class="dropdown-item" href="#">Profile</a></li>
      <li><hr class="dropdown-divider"></li>
      <li><a class="dropdown-item" href="#">Sign out</a></li>
    </ul>
  </div> -->
</div>

<?php require APPROOT . '/views/inc/footer_admin.php'; ?>