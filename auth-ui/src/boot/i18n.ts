import {boot} from 'quasar/wrappers';
import messages from 'src/i18n';
import Vue, {ComponentOptions} from 'vue';
import VueI18n from 'vue-i18n';

declare module 'vue/types/vue' {
  interface Vue {
    i18n: VueI18n;

    $tt(context: any, key: string): string | null;
  }
}

const t = function (key: string) {
  var values = [], len = arguments.length - 1;
  while (len-- > 0) values[len] = arguments[len + 1];
  // @ts-ignore
  return i18n._t.apply(i18n, [key, i18n.locale, i18n._getMessages(), this].concat(values))
};

const tt = function (context: string | Vue | ComponentOptions<Vue>, key: string) {
  if (context == null)
    return t(key);
  if (typeof context === "string") {
    if (key == null)
      return t(context);
    return context.endsWith('.') ? context + key : context + "." + key;
  } else if (context instanceof Vue) {
    if (key == null)
      return t((<Vue>context).$options.name || key);
    return t(((<Vue>context).$options.name || "") + "." + key)
  } else {
    let c = <ComponentOptions<Vue>>context;
    if (c == null || c.name == null)
      return t(key);
    if (key == null)
      return t(c.name);
    return t(c.name + "." + key);
  }
};

Vue.use(VueI18n);

export const i18n = new VueI18n({
  locale: "en-us",
  fallbackLocale: "zh-hans",
  messages
});

export default boot(({app, Vue}) => {
  // Set i18n instance on app
  app.i18n = i18n;
  Vue.prototype.$tt = tt;
  Vue.prototype.$options
});
