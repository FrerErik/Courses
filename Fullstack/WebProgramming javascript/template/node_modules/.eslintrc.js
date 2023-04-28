module.exports = {
    "env": {
        "browser": true,
        "es2021": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:react/recommended"
    ],
    "overrides": [
    ],
    "parserOptions": {
        "ecmaVersion": "latest",
        "sourceType": "module"
    },
    "plugins": [
        "react",
        "jsdoc"
    ],
    extends: [
      'plugin:jsdoc/recommended'
    ],
  
    rules: {
      // jsdoc to extend the recommended ruleset
      'jsdoc/require-jsdoc': [1, {
        require: {
          ClassDeclaration: true,
          MethodDefinition: true
        }
      }],
      'jsdoc/require-description': 1,
      'jsdoc/require-description-complete-sentence': 1
    }
  
}
