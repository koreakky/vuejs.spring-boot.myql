// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {
  'login tests': function(browser) => {
    browser
      .url(process.env.VUE_DEV_SERVER_URL + 'login')
      .waitForElementVisible('#app', 5000)
      .assert.containText('h1', 'TaskAgile')
      .end()
  }
}
