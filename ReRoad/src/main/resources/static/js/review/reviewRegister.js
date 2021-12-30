window.addEventListener('load', function() {
    const saveBtn = document.getElementById('saveBtn');
    saveBtn.addEventListener('click', function() {
        const form = document.getElementById('form');
        form.submit();
    });
})