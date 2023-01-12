const usernameInput = document.querySelector('#username');
const span = document.querySelector('#username + span');

usernameInput.addEventListener('change', async e => {
  const username = usernameInput.value;
  const response = await fetch(`ajax_user_exists.php?username=${encodeURIComponent(username)}`);
  const result = await response.json();
  console.log(result);
  span.innerHTML = result ? "OK" : "Error: Existing username"
})