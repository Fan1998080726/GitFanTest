"use strict";
var sjcl = {
    cipher: {}, hash: {}, keyexchange: {}, mode: {}, misc: {}, codec: {}, exception: {
        corrupt    : function (a) {
            this.toString = function () {
                return "CORRUPT: " + this.message
            };
            this.message = a
        }, invalid : function (a) {
            this.toString = function () {
                return "INVALID: " + this.message
            };
            this.message = a
        }, bug     : function (a) {
            this.toString = function () {
                return "BUG: " + this.message
            };
            this.message = a
        }, notReady: function (a) {
            this.toString = function () {
                return "NOT READY: " + this.message
            };
            this.message = a
        }
    }
};
sjcl.cipher.aes = function (a) {
    this.J[0][0][0] || this.aa ();
    var b, c, d, e, f = this.J[0][4], g = this.J[1];
    b = a.length;
    var h = 1;
    if (4 !== b && 6 !== b && 8 !== b) throw new sjcl.exception.invalid ("invalid aes key size");
    this.f = [d = a.slice (0), e = []];
    for (a = b; a < 4 * b + 28; a++) {
        c = d[a - 1];
        if (0 === a % b || 8 === b && 4 === a % b) c = f[c >>> 24] << 24 ^ f[c >> 16 & 255] << 16 ^ f[c >> 8 & 255] << 8 ^ f[c & 255], 0 === a % b && (c = c << 8 ^ c >>> 24 ^ h << 24, h = h << 1 ^ 283 * (h >> 7));
        d[a] = d[a - b] ^ c
    }
    for (b = 0; a; b++, a--) c = d[b & 3 ? a : a - 4], e[b] = 4 >= a || 4 > b ? c : g[0][f[c >>> 24]] ^ g[1][f[c >> 16 & 255]] ^ g[2][f[c >> 8 & 255]] ^
            g[3][f[c & 255]]
};
sjcl.cipher.aes.prototype = {
    encrypt   : function (a) {
        return t (this, a, 0)
    }, decrypt: function (a) {
        return t (this, a, 1)
    }, J      : [[[], [], [], [], []], [[], [], [], [], []]], aa: function () {
        var a = this.J[0], b = this.J[1], c = a[4], d = b[4], e, f, g, h = [], k = [], l, m, n, p;
        for (e = 0; 0x100 > e; e++) k[(h[e] = e << 1 ^ 283 * (e >> 7)) ^ e] = e;
        for (f = g = 0; !c[f]; f ^= l || 1, g = k[g] || 1) for (n = g ^ g << 1 ^ g << 2 ^ g << 3 ^ g << 4, n = n >> 8 ^ n & 255 ^ 99, c[f] = n, d[n] = f, m = h[e = h[l = h[f]]], p = 0x1010101 * m ^ 0x10001 * e ^ 0x101 * l ^ 0x1010100 * f, m = 0x101 * h[n] ^ 0x1010100 * n, e = 0; 4 > e; e++) a[e][f] = m = m << 24 ^ m >>> 8, b[e][n] = p = p << 24 ^ p >>> 8;
        for (e =
                     0; 5 > e; e++) a[e] = a[e].slice (0), b[e] = b[e].slice (0)
    }
};

function t (a, b, c) {
    if (4 !== b.length) throw new sjcl.exception.invalid ("invalid aes block size");
    var d = a.f[c], e = b[0] ^ d[0], f = b[c ? 3 : 1] ^ d[1], g = b[2] ^ d[2];
    b = b[c ? 1 : 3] ^ d[3];
    var h, k, l, m = d.length / 4 - 2, n, p = 4, r = [0, 0, 0, 0];
    h = a.J[c];
    a = h[0];
    var q = h[1], w = h[2], x = h[3], y = h[4];
    for (n = 0; n < m; n++) h = a[e >>> 24] ^ q[f >> 16 & 255] ^ w[g >> 8 & 255] ^ x[b & 255] ^ d[p], k = a[f >>> 24] ^ q[g >> 16 & 255] ^ w[b >> 8 & 255] ^ x[e & 255] ^ d[p + 1], l = a[g >>> 24] ^ q[b >> 16 & 255] ^ w[e >> 8 & 255] ^ x[f & 255] ^ d[p + 2], b = a[b >>> 24] ^ q[e >> 16 & 255] ^ w[f >> 8 & 255] ^ x[g & 255] ^ d[p + 3], p += 4, e = h, f = k, g = l;
    for (n =
                 0; 4 > n; n++) r[c ? 3 & -n : n] = y[e >>> 24] << 24 ^ y[f >> 16 & 255] << 16 ^ y[g >> 8 & 255] << 8 ^ y[b & 255] ^ d[p++], h = e, e = f, f = g, g = b, b = h;
    return r
}

sjcl.bitArray = {
    bitSlice     : function (a, b, c) {
        a = sjcl.bitArray.oa (a.slice (b / 32), 32 - (b & 31)).slice (1);
        return void 0 === c ? a : sjcl.bitArray.clamp (a, c - b)
    }, extract   : function (a, b, c) {
        var d = Math.floor (-b - c & 31);
        return ((b + c - 1 ^ b) & -32 ? a[b / 32 | 0] << 32 - d ^ a[b / 32 + 1 | 0] >>> d : a[b / 32 | 0] >>> d) & (1 << c) - 1
    }, concat    : function (a, b) {
        if (0 === a.length || 0 === b.length) return a.concat (b);
        var c = a[a.length - 1], d = sjcl.bitArray.getPartial (c);
        return 32 === d ? a.concat (b) : sjcl.bitArray.oa (b, d, c | 0, a.slice (0, a.length - 1))
    }, bitLength : function (a) {
        var b = a.length;
        return 0 === b ? 0 : 32 * (b - 1) + sjcl.bitArray.getPartial (a[b - 1])
    }, clamp     : function (a, b) {
        if (32 * a.length < b) return a;
        a = a.slice (0, Math.ceil (b / 32));
        var c = a.length;
        b = b & 31;
        0 < c && b && (a[c - 1] = sjcl.bitArray.partial (b, a[c - 1] & 2147483648 >> b - 1, 1));
        return a
    }, partial   : function (a, b, c) {
        return 32 === a ? b : (c ? b | 0 : b << 32 - a) + 0x10000000000 * a
    }, getPartial: function (a) {
        return Math.round (a / 0x10000000000) || 32
    }, equal     : function (a, b) {
        if (sjcl.bitArray.bitLength (a) !== sjcl.bitArray.bitLength (b)) return !1;
        var c = 0, d;
        for (d = 0; d < a.length; d++) c |= a[d] ^ b[d];
        return 0 === c
    }, oa        : function (a, b, c, d) {
        var e;
        e = 0;
        for (void 0 === d && (d = []); 32 <= b; b -= 32) d.push (c), c = 0;
        if (0 === b) return d.concat (a);
        for (e = 0; e < a.length; e++) d.push (c | a[e] >>> b), c = a[e] << 32 - b;
        e = a.length ? a[a.length - 1] : 0;
        a = sjcl.bitArray.getPartial (e);
        d.push (sjcl.bitArray.partial (b + a & 31, 32 < b + a ? c : d.pop (), 1));
        return d
    }, C         : function (a, b) {
        return [a[0] ^ b[0], a[1] ^ b[1], a[2] ^ b[2], a[3] ^ b[3]]
    }, byteswapM : function (a) {
        var b, c;
        for (b = 0; b < a.length; ++b) c = a[b], a[b] = c >>> 24 | c >>> 8 & 0xff00 | (c & 0xff00) << 8 | c << 24;
        return a
    }
};
sjcl.codec.utf8String = {
    fromBits : function (a) {
        var b = "", c = sjcl.bitArray.bitLength (a), d, e;
        for (d = 0; d < c / 8; d++) 0 === (d & 3) && (e = a[d / 4]), b += String.fromCharCode (e >>> 8 >>> 8 >>> 8), e <<= 8;
        return decodeURIComponent (escape (b))
    }, toBits: function (a) {
        a = unescape (encodeURIComponent (a));
        var b = [], c, d = 0;
        for (c = 0; c < a.length; c++) d = d << 8 | a.charCodeAt (c), 3 === (c & 3) && (b.push (d), d = 0);
        c & 3 && b.push (sjcl.bitArray.partial (8 * (c & 3), d));
        return b
    }
};
sjcl.codec.hex = {
    fromBits : function (a) {
        var b = "", c;
        for (c = 0; c < a.length; c++) b += ((a[c] | 0) + 0xf00000000000).toString (16).substr (4);
        return b.substr (0, sjcl.bitArray.bitLength (a) / 4)
    }, toBits: function (a) {
        var b, c = [], d;
        a = a.replace (/\s|0x/g, "");
        d = a.length;
        a = a + "00000000";
        for (b = 0; b < a.length; b += 8) c.push (parseInt (a.substr (b, 8), 16) ^ 0);
        return sjcl.bitArray.clamp (c, 4 * d)
    }
};
sjcl.codec.base32 = {
    M        : "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567",
    ja       : "0123456789ABCDEFGHIJKLMNOPQRSTUV",
    BITS     : 32,
    BASE     : 5,
    REMAINING: 27,
    fromBits : function (a, b, c) {
        var d = sjcl.codec.base32.BASE, e = sjcl.codec.base32.REMAINING, f = "", g = 0, h = sjcl.codec.base32.M, k = 0,
                l = sjcl.bitArray.bitLength (a);
        c && (h = sjcl.codec.base32.ja);
        for (c = 0; f.length * d < l;) f += h.charAt ((k ^ a[c] >>> g) >>> e), g < d ? (k = a[c] << d - g, g += e, c++) : (k <<= d, g -= d);
        for (; f.length & 7 && !b;) f += "=";
        return f
    },
    toBits   : function (a, b) {
        a = a.replace (/\s|=/g, "").toUpperCase ();
        var c = sjcl.codec.base32.BITS,
                d = sjcl.codec.base32.BASE, e = sjcl.codec.base32.REMAINING, f = [], g, h = 0, k = sjcl.codec.base32.M,
                l = 0, m, n = "base32";
        b && (k = sjcl.codec.base32.ja, n = "base32hex");
        for (g = 0; g < a.length; g++) {
            m = k.indexOf (a.charAt (g));
            if (0 > m) {
                if (!b) try {
                    return sjcl.codec.base32hex.toBits (a)
                } catch (p) {
                }
                throw new sjcl.exception.invalid ("this isn't " + n + "!");
            }
            h > e ? (h -= e, f.push (l ^ m >>> h), l = m << c - h) : (h += d, l ^= m << c - h)
        }
        h & 56 && f.push (sjcl.bitArray.partial (h & 56, l, 1));
        return f
    }
};
sjcl.codec.base32hex = {
    fromBits : function (a, b) {
        return sjcl.codec.base32.fromBits (a, b, 1)
    }, toBits: function (a) {
        return sjcl.codec.base32.toBits (a, 1)
    }
};
sjcl.codec.base64 = {
    M        : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", fromBits: function (a, b, c) {
        var d = "", e = 0, f = sjcl.codec.base64.M, g = 0, h = sjcl.bitArray.bitLength (a);
        c && (f = f.substr (0, 62) + "-_");
        for (c = 0; 6 * d.length < h;) d += f.charAt ((g ^ a[c] >>> e) >>> 26), 6 > e ? (g = a[c] << 6 - e, e += 26, c++) : (g <<= 6, e -= 6);
        for (; d.length & 3 && !b;) d += "=";
        return d
    }, toBits: function (a, b) {
        a = a.replace (/\s|=/g, "");
        var c = [], d, e = 0, f = sjcl.codec.base64.M, g = 0, h;
        b && (f = f.substr (0, 62) + "-_");
        for (d = 0; d < a.length; d++) {
            h = f.indexOf (a.charAt (d));
            if (0 > h) throw new sjcl.exception.invalid ("this isn't base64!");
            26 < e ? (e -= 26, c.push (g ^ h >>> e), g = h << 32 - e) : (e += 6, g ^= h << 32 - e)
        }
        e & 56 && c.push (sjcl.bitArray.partial (e & 56, g, 1));
        return c
    }
};
sjcl.codec.base64url = {
    fromBits : function (a) {
        return sjcl.codec.base64.fromBits (a, 1, 1)
    }, toBits: function (a) {
        return sjcl.codec.base64.toBits (a, 1)
    }
};
sjcl.hash.sha256 = function (a) {
    this.f[0] || this.aa ();
    a ? (this.l = a.l.slice (0), this.i = a.i.slice (0), this.h = a.h) : this.reset ()
};
sjcl.hash.sha256.hash = function (a) {
    return (new sjcl.hash.sha256).update (a).finalize ()
};
sjcl.hash.sha256.prototype = {
    blockSize  : 512, reset: function () {
        this.l = this.S.slice (0);
        this.i = [];
        this.h = 0;
        return this
    }, update  : function (a) {
        "string" === typeof a && (a = sjcl.codec.utf8String.toBits (a));
        var b, c = this.i = sjcl.bitArray.concat (this.i, a);
        b = this.h;
        a = this.h = b + sjcl.bitArray.bitLength (a);
        if (0x1fffffffffffff < a) throw new sjcl.exception.invalid ("Cannot hash more than 2^53 - 1 bits");
        if ("undefined" !== typeof Uint32Array) {
            var d = new Uint32Array (c), e = 0;
            for (b = 512 + b - (512 + b & 0x1ff); b <= a; b += 512) this.w (d.subarray (16 * e,
                    16 * (e + 1))), e += 1;
            c.splice (0, 16 * e)
        } else for (b = 512 + b - (512 + b & 0x1ff); b <= a; b += 512) this.w (c.splice (0, 16));
        return this
    }, finalize: function () {
        var a, b = this.i, c = this.l, b = sjcl.bitArray.concat (b, [sjcl.bitArray.partial (1, 1)]);
        for (a = b.length + 2; a & 15; a++) b.push (0);
        b.push (Math.floor (this.h / 0x100000000));
        for (b.push (this.h | 0); b.length;) this.w (b.splice (0, 16));
        this.reset ();
        return c
    }, S       : [], f: [], aa: function () {
        function a (a) {
            return 0x100000000 * (a - Math.floor (a)) | 0
        }

        for (var b = 0, c = 2, d, e; 64 > b; c++) {
            e = !0;
            for (d = 2; d * d <= c; d++) if (0 === c % d) {
                e =
                        !1;
                break
            }
            e && (8 > b && (this.S[b] = a (Math.pow (c, .5))), this.f[b] = a (Math.pow (c, 1 / 3)), b++)
        }
    }, w       : function (a) {
        var b, c, d, e = this.l, f = this.f, g = e[0], h = e[1], k = e[2], l = e[3], m = e[4], n = e[5], p = e[6],
                r = e[7];
        for (b = 0; 64 > b; b++) 16 > b ? c = a[b] : (c = a[b + 1 & 15], d = a[b + 14 & 15], c = a[b & 15] = (c >>> 7 ^ c >>> 18 ^ c >>> 3 ^ c << 25 ^ c << 14) + (d >>> 17 ^ d >>> 19 ^ d >>> 10 ^ d << 15 ^ d << 13) + a[b & 15] + a[b + 9 & 15] | 0), c = c + r + (m >>> 6 ^ m >>> 11 ^ m >>> 25 ^ m << 26 ^ m << 21 ^ m << 7) + (p ^ m & (n ^ p)) + f[b], r = p, p = n, n = m, m = l + c | 0, l = k, k = h, h = g, g = c + (h & k ^ l & (h ^ k)) + (h >>> 2 ^ h >>> 13 ^ h >>> 22 ^ h << 30 ^ h << 19 ^ h << 10) | 0;
        e[0] = e[0] + g |
                0;
        e[1] = e[1] + h | 0;
        e[2] = e[2] + k | 0;
        e[3] = e[3] + l | 0;
        e[4] = e[4] + m | 0;
        e[5] = e[5] + n | 0;
        e[6] = e[6] + p | 0;
        e[7] = e[7] + r | 0
    }
};
sjcl.hash.sha1 = function (a) {
    a ? (this.l = a.l.slice (0), this.i = a.i.slice (0), this.h = a.h) : this.reset ()
};
sjcl.hash.sha1.hash = function (a) {
    return (new sjcl.hash.sha1).update (a).finalize ()
};
sjcl.hash.sha1.prototype = {
    blockSize  : 512, reset: function () {
        this.l = this.S.slice (0);
        this.i = [];
        this.h = 0;
        return this
    }, update  : function (a) {
        "string" === typeof a && (a = sjcl.codec.utf8String.toBits (a));
        var b, c = this.i = sjcl.bitArray.concat (this.i, a);
        b = this.h;
        a = this.h = b + sjcl.bitArray.bitLength (a);
        if (0x1fffffffffffff < a) throw new sjcl.exception.invalid ("Cannot hash more than 2^53 - 1 bits");
        if ("undefined" !== typeof Uint32Array) {
            var d = new Uint32Array (c), e = 0;
            for (b = this.blockSize + b - (this.blockSize + b & this.blockSize - 1); b <=
            a; b += this.blockSize) this.w (d.subarray (16 * e, 16 * (e + 1))), e += 1;
            c.splice (0, 16 * e)
        } else for (b = this.blockSize + b - (this.blockSize + b & this.blockSize - 1); b <= a; b += this.blockSize) this.w (c.splice (0, 16));
        return this
    }, finalize: function () {
        var a, b = this.i, c = this.l, b = sjcl.bitArray.concat (b, [sjcl.bitArray.partial (1, 1)]);
        for (a = b.length + 2; a & 15; a++) b.push (0);
        b.push (Math.floor (this.h / 0x100000000));
        for (b.push (this.h | 0); b.length;) this.w (b.splice (0, 16));
        this.reset ();
        return c
    }, S       : [1732584193, 4023233417, 2562383102, 271733878, 3285377520],
    f          : [1518500249, 1859775393, 2400959708, 3395469782], w: function (a) {
        var b, c, d, e, f, g, h = this.l, k;
        if ("undefined" !== typeof Uint32Array) for (k = Array (80), c = 0; 16 > c; c++) k[c] = a[c]; else k = a;
        c = h[0];
        d = h[1];
        e = h[2];
        f = h[3];
        g = h[4];
        for (a = 0; 79 >= a; a++) 16 <= a && (b = k[a - 3] ^ k[a - 8] ^ k[a - 14] ^ k[a - 16], k[a] = b << 1 | b >>> 31), b = 19 >= a ? d & e | ~d & f : 39 >= a ? d ^ e ^ f : 59 >= a ? d & e | d & f | e & f : 79 >= a ? d ^ e ^ f : void 0, b = (c << 5 | c >>> 27) + b + g + k[a] + this.f[Math.floor (a / 20)] | 0, g = f, f = e, e = d << 30 | d >>> 2, d = c, c = b;
        h[0] = h[0] + c | 0;
        h[1] = h[1] + d | 0;
        h[2] = h[2] + e | 0;
        h[3] = h[3] + f | 0;
        h[4] = h[4] + g | 0
    }
};
sjcl.mode.ccm = {
    name               : "ccm", T: [], listenProgress: function (a) {
        sjcl.mode.ccm.T.push (a)
    }, unListenProgress: function (a) {
        a = sjcl.mode.ccm.T.indexOf (a);
        -1 < a && sjcl.mode.ccm.T.splice (a, 1)
    }, va              : function (a) {
        var b = sjcl.mode.ccm.T.slice (), c;
        for (c = 0; c < b.length; c += 1) b[c] (a)
    }, encrypt         : function (a, b, c, d, e) {
        var f, g = b.slice (0), h = sjcl.bitArray, k = h.bitLength (c) / 8, l = h.bitLength (g) / 8;
        e = e || 64;
        d = d || [];
        if (7 > k) throw new sjcl.exception.invalid ("ccm: iv must be at least 7 bytes");
        for (f = 2; 4 > f && l >>> 8 * f; f++) ;
        f < 15 - k && (f = 15 - k);
        c = h.clamp (c,
                8 * (15 - f));
        b = sjcl.mode.ccm.ga (a, b, c, d, e, f);
        g = sjcl.mode.ccm.O (a, g, c, b, e, f);
        return h.concat (g.data, g.tag)
    }, decrypt         : function (a, b, c, d, e) {
        e = e || 64;
        d = d || [];
        var f = sjcl.bitArray, g = f.bitLength (c) / 8, h = f.bitLength (b), k = f.clamp (b, h - e),
                l = f.bitSlice (b, h - e), h = (h - e) / 8;
        if (7 > g) throw new sjcl.exception.invalid ("ccm: iv must be at least 7 bytes");
        for (b = 2; 4 > b && h >>> 8 * b; b++) ;
        b < 15 - g && (b = 15 - g);
        c = f.clamp (c, 8 * (15 - b));
        k = sjcl.mode.ccm.O (a, k, c, l, e, b);
        a = sjcl.mode.ccm.ga (a, k.data, c, d, e, b);
        if (!f.equal (k.tag, a)) throw new sjcl.exception.corrupt ("ccm: tag doesn't match");
        return k.data
    }, Ea              : function (a, b, c, d, e, f) {
        var g = [], h = sjcl.bitArray, k = h.C;
        d = [h.partial (8, (b.length ? 64 : 0) | d - 2 << 2 | f - 1)];
        d = h.concat (d, c);
        d[3] |= e;
        d = a.encrypt (d);
        if (b.length) for (c = h.bitLength (b) / 8, 65279 >= c ? g = [h.partial (16, c)] : 0xffffffff >= c && (g = h.concat ([h.partial (16, 65534)], [c])), g = h.concat (g, b), b = 0; b < g.length; b += 4) d = a.encrypt (k (d, g.slice (b, b + 4).concat ([0, 0, 0])));
        return d
    }, ga              : function (a, b, c, d, e, f) {
        var g = sjcl.bitArray, h = g.C;
        e /= 8;
        if (e % 2 || 4 > e || 16 < e) throw new sjcl.exception.invalid ("ccm: invalid tag length");
        if (0xffffffff < d.length || 0xffffffff < b.length) throw new sjcl.exception.bug ("ccm: can't deal with 4GiB or more data");
        c = sjcl.mode.ccm.Ea (a, d, c, e, g.bitLength (b) / 8, f);
        for (d = 0; d < b.length; d += 4) c = a.encrypt (h (c, b.slice (d, d + 4).concat ([0, 0, 0])));
        return g.clamp (c, 8 * e)
    }, O               : function (a, b, c, d, e, f) {
        var g, h = sjcl.bitArray;
        g = h.C;
        var k = b.length, l = h.bitLength (b), m = k / 50, n = m;
        c = h.concat ([h.partial (8, f - 1)], c).concat ([0, 0, 0]).slice (0, 4);
        d = h.bitSlice (g (d, a.encrypt (c)), 0, e);
        if (!k) return {tag: d, data: []};
        for (g = 0; g < k; g += 4) g > m && (sjcl.mode.ccm.va (g /
                k), m += n), c[3]++, e = a.encrypt (c), b[g] ^= e[0], b[g + 1] ^= e[1], b[g + 2] ^= e[2], b[g + 3] ^= e[3];
        return {tag: d, data: h.clamp (b, l)}
    }
};
sjcl.mode.ocb2 = {
    name      : "ocb2", encrypt: function (a, b, c, d, e, f) {
        if (128 !== sjcl.bitArray.bitLength (c)) throw new sjcl.exception.invalid ("ocb iv must be 128 bits");
        var g, h = sjcl.mode.ocb2.da, k = sjcl.bitArray, l = k.C, m = [0, 0, 0, 0];
        c = h (a.encrypt (c));
        var n, p = [];
        d = d || [];
        e = e || 64;
        for (g = 0; g + 4 < b.length; g += 4) n = b.slice (g, g + 4), m = l (m, n), p = p.concat (l (c, a.encrypt (l (c, n)))), c = h (c);
        n = b.slice (g);
        b = k.bitLength (n);
        g = a.encrypt (l (c, [0, 0, 0, b]));
        n = k.clamp (l (n.concat ([0, 0, 0]), g), b);
        m = l (m, l (n.concat ([0, 0, 0]), g));
        m = a.encrypt (l (m, l (c, h (c))));
        d.length && (m = l (m, f ? d : sjcl.mode.ocb2.pmac (a, d)));
        return p.concat (k.concat (n, k.clamp (m, e)))
    }, decrypt: function (a, b, c, d, e, f) {
        if (128 !== sjcl.bitArray.bitLength (c)) throw new sjcl.exception.invalid ("ocb iv must be 128 bits");
        e = e || 64;
        var g = sjcl.mode.ocb2.da, h = sjcl.bitArray, k = h.C, l = [0, 0, 0, 0], m = g (a.encrypt (c)), n, p,
                r = sjcl.bitArray.bitLength (b) - e, q = [];
        d = d || [];
        for (c = 0; c + 4 < r / 32; c += 4) n = k (m, a.decrypt (k (m, b.slice (c, c + 4)))), l = k (l, n), q = q.concat (n), m = g (m);
        p = r - 32 * c;
        n = a.encrypt (k (m, [0, 0, 0, p]));
        n = k (n, h.clamp (b.slice (c),
                p).concat ([0, 0, 0]));
        l = k (l, n);
        l = a.encrypt (k (l, k (m, g (m))));
        d.length && (l = k (l, f ? d : sjcl.mode.ocb2.pmac (a, d)));
        if (!h.equal (h.clamp (l, e), h.bitSlice (b, r))) throw new sjcl.exception.corrupt ("ocb: tag doesn't match");
        return q.concat (h.clamp (n, p))
    }, pmac   : function (a, b) {
        var c, d = sjcl.mode.ocb2.da, e = sjcl.bitArray, f = e.C, g = [0, 0, 0, 0], h = a.encrypt ([0, 0, 0, 0]),
                h = f (h, d (d (h)));
        for (c = 0; c + 4 < b.length; c += 4) h = d (h), g = f (g, a.encrypt (f (h, b.slice (c, c + 4))));
        c = b.slice (c);
        128 > e.bitLength (c) && (h = f (h, d (h)), c = e.concat (c, [-2147483648, 0, 0,
            0]));
        g = f (g, c);
        return a.encrypt (f (d (f (h, d (h))), g))
    }, da     : function (a) {
        return [a[0] << 1 ^ a[1] >>> 31, a[1] << 1 ^ a[2] >>> 31, a[2] << 1 ^ a[3] >>> 31, a[3] << 1 ^ 135 * (a[0] >>> 31)]
    }
};
sjcl.mode.gcm = {
    name      : "gcm", encrypt: function (a, b, c, d, e) {
        var f = b.slice (0);
        b = sjcl.bitArray;
        d = d || [];
        a = sjcl.mode.gcm.O (!0, a, f, d, c, e || 128);
        return b.concat (a.data, a.tag)
    }, decrypt: function (a, b, c, d, e) {
        var f = b.slice (0), g = sjcl.bitArray, h = g.bitLength (f);
        e = e || 128;
        d = d || [];
        e <= h ? (b = g.bitSlice (f, h - e), f = g.bitSlice (f, 0, h - e)) : (b = f, f = []);
        a = sjcl.mode.gcm.O (!1, a, f, d, c, e);
        if (!g.equal (a.tag, b)) throw new sjcl.exception.corrupt ("gcm: tag doesn't match");
        return a.data
    }, Aa     : function (a, b) {
        var c, d, e, f, g, h = sjcl.bitArray.C;
        e = [0, 0,
            0, 0];
        f = b.slice (0);
        for (c = 0; 128 > c; c++) {
            (d = 0 !== (a[Math.floor (c / 32)] & 1 << 31 - c % 32)) && (e = h (e, f));
            g = 0 !== (f[3] & 1);
            for (d = 3; 0 < d; d--) f[d] = f[d] >>> 1 | (f[d - 1] & 1) << 31;
            f[0] >>>= 1;
            g && (f[0] ^= -0x1f000000)
        }
        return e
    }, F      : function (a, b, c) {
        var d, e = c.length;
        b = b.slice (0);
        for (d = 0; d < e; d += 4) b[0] ^= 0xffffffff & c[d], b[1] ^= 0xffffffff & c[d + 1], b[2] ^= 0xffffffff & c[d + 2], b[3] ^= 0xffffffff & c[d + 3], b = sjcl.mode.gcm.Aa (b, a);
        return b
    }, O      : function (a, b, c, d, e, f) {
        var g, h, k, l, m, n, p, r, q = sjcl.bitArray;
        n = c.length;
        p = q.bitLength (c);
        r = q.bitLength (d);
        h = q.bitLength (e);
        g = b.encrypt ([0, 0, 0, 0]);
        96 === h ? (e = e.slice (0), e = q.concat (e, [1])) : (e = sjcl.mode.gcm.F (g, [0, 0, 0, 0], e), e = sjcl.mode.gcm.F (g, e, [0, 0, Math.floor (h / 0x100000000), h & 0xffffffff]));
        h = sjcl.mode.gcm.F (g, [0, 0, 0, 0], d);
        m = e.slice (0);
        d = h.slice (0);
        a || (d = sjcl.mode.gcm.F (g, h, c));
        for (l = 0; l < n; l += 4) m[3]++, k = b.encrypt (m), c[l] ^= k[0], c[l + 1] ^= k[1], c[l + 2] ^= k[2], c[l + 3] ^= k[3];
        c = q.clamp (c, p);
        a && (d = sjcl.mode.gcm.F (g, h, c));
        a = [Math.floor (r / 0x100000000), r & 0xffffffff, Math.floor (p / 0x100000000), p & 0xffffffff];
        d = sjcl.mode.gcm.F (g, d, a);
        k = b.encrypt (e);
        d[0] ^= k[0];
        d[1] ^= k[1];
        d[2] ^= k[2];
        d[3] ^= k[3];
        return {tag: q.bitSlice (d, 0, f), data: c}
    }
};
sjcl.misc.hmac = function (a, b) {
    this.ia = b = b || sjcl.hash.sha256;
    var c = [[], []], d, e = b.prototype.blockSize / 32;
    this.L = [new b, new b];
    a.length > e && (a = b.hash (a));
    for (d = 0; d < e; d++) c[0][d] = a[d] ^ 909522486, c[1][d] = a[d] ^ 1549556828;
    this.L[0].update (c[0]);
    this.L[1].update (c[1]);
    this.ca = new b (this.L[0])
};
sjcl.misc.hmac.prototype.encrypt = sjcl.misc.hmac.prototype.mac = function (a) {
    if (this.qa) throw new sjcl.exception.invalid ("encrypt on already updated hmac called!");
    this.update (a);
    return this.digest (a)
};
sjcl.misc.hmac.prototype.reset = function () {
    this.ca = new this.ia (this.L[0]);
    this.qa = !1
};
sjcl.misc.hmac.prototype.update = function (a) {
    this.qa = !0;
    this.ca.update (a)
};
sjcl.misc.hmac.prototype.digest = function () {
    var a = this.ca.finalize (), a = (new this.ia (this.L[1])).update (a).finalize ();
    this.reset ();
    return a
};
sjcl.misc.pbkdf2 = function (a, b, c, d, e) {
    c = c || 1E4;
    if (0 > d || 0 > c) throw new sjcl.exception.invalid ("invalid params to pbkdf2");
    "string" === typeof a && (a = sjcl.codec.utf8String.toBits (a));
    "string" === typeof b && (b = sjcl.codec.utf8String.toBits (b));
    e = e || sjcl.misc.hmac;
    a = new e (a);
    var f, g, h, k, l = [], m = sjcl.bitArray;
    for (k = 1; 32 * l.length < (d || 1); k++) {
        e = f = a.encrypt (m.concat (b, [k]));
        for (g = 1; g < c; g++) for (f = a.encrypt (f), h = 0; h < f.length; h++) e[h] ^= f[h];
        l = l.concat (e)
    }
    d && (l = m.clamp (l, d));
    return l
};
sjcl.prng = function (a) {
    this.m = [new sjcl.hash.sha256];
    this.H = [0];
    this.ba = 0;
    this.U = {};
    this.$ = 0;
    this.fa = {};
    this.na = this.s = this.I = this.xa = 0;
    this.f = [0, 0, 0, 0, 0, 0, 0, 0];
    this.A = [0, 0, 0, 0];
    this.Y = void 0;
    this.Z = a;
    this.R = !1;
    this.X = {progress: {}, seeded: {}};
    this.K = this.wa = 0;
    this.V = 1;
    this.W = 2;
    this.sa = 0x10000;
    this.ea = [0, 48, 64, 96, 128, 192, 0x100, 384, 512, 768, 1024];
    this.ta = 3E4;
    this.ra = 80
};
sjcl.prng.prototype = {
    randomWords           : function (a, b) {
        var c = [], d;
        d = this.isReady (b);
        var e;
        if (d === this.K) throw new sjcl.exception.notReady ("generator isn't seeded");
        if (d & this.W) {
            d = !(d & this.V);
            e = [];
            var f = 0, g;
            this.na = e[0] = (new Date).valueOf () + this.ta;
            for (g = 0; 16 > g; g++) e.push (0x100000000 * Math.random () | 0);
            for (g = 0; g < this.m.length && (e = e.concat (this.m[g].finalize ()), f += this.H[g], this.H[g] = 0, d || !(this.ba & 1 << g)); g++) ;
            this.ba >= 1 << this.m.length && (this.m.push (new sjcl.hash.sha256), this.H.push (0));
            this.s -= f;
            f > this.I && (this.I =
                    f);
            this.ba++;
            this.f = sjcl.hash.sha256.hash (this.f.concat (e));
            this.Y = new sjcl.cipher.aes (this.f);
            for (d = 0; 4 > d && (this.A[d] = this.A[d] + 1 | 0, !this.A[d]); d++) ;
        }
        for (d = 0; d < a; d += 4) 0 === (d + 1) % this.sa && u (this), e = v (this), c.push (e[0], e[1], e[2], e[3]);
        u (this);
        return c.slice (0, a)
    }, setDefaultParanoia : function (a, b) {
        if (0 === a && "Setting paranoia=0 will ruin your security; use it only for testing" !== b) throw new sjcl.exception.invalid ("Setting paranoia=0 will ruin your security; use it only for testing");
        this.Z = a
    }, addEntropy         : function (a,
                                      b, c) {
        c = c || "user";
        var d, e, f = (new Date).valueOf (), g = this.U[c], h = this.isReady (), k = 0;
        d = this.fa[c];
        void 0 === d && (d = this.fa[c] = this.xa++);
        void 0 === g && (g = this.U[c] = 0);
        this.U[c] = (this.U[c] + 1) % this.m.length;
        switch (typeof a) {
            case "number":
                void 0 === b && (b = 1);
                this.m[g].update ([d, this.$++, 1, b, f, 1, a | 0]);
                break;
            case "object":
                c = Object.prototype.toString.call (a);
                if ("[object Uint32Array]" === c) {
                    e = [];
                    for (c = 0; c < a.length; c++) e.push (a[c]);
                    a = e
                } else for ("[object Array]" !== c && (k = 1), c = 0; c < a.length && !k; c++) "number" !== typeof a[c] &&
                (k = 1);
                if (!k) {
                    if (void 0 === b) for (c = b = 0; c < a.length; c++) for (e = a[c]; 0 < e;) b++, e = e >>> 1;
                    this.m[g].update ([d, this.$++, 2, b, f, a.length].concat (a))
                }
                break;
            case "string":
                void 0 === b && (b = a.length);
                this.m[g].update ([d, this.$++, 3, b, f, a.length]);
                this.m[g].update (a);
                break;
            default:
                k = 1
        }
        if (k) throw new sjcl.exception.bug ("random: addEntropy only supports number, array of numbers or string");
        this.H[g] += b;
        this.s += b;
        h === this.K && (this.isReady () !== this.K && z ("seeded", Math.max (this.I, this.s)), z ("progress", this.getProgress ()))
    },
    isReady               : function (a) {
        a = this.ea[void 0 !== a ? a : this.Z];
        return this.I && this.I >= a ? this.H[0] > this.ra && (new Date).valueOf () > this.na ? this.W | this.V : this.V : this.s >= a ? this.W | this.K : this.K
    }, getProgress        : function (a) {
        a = this.ea[a ? a : this.Z];
        return this.I >= a ? 1 : this.s > a ? 1 : this.s / a
    }, startCollectors    : function () {
        if (!this.R) {
            this.c = {
                loadTimeCollector     : A (this, this.Da),
                mouseCollector        : A (this, this.Fa),
                keyboardCollector     : A (this, this.Ca),
                accelerometerCollector: A (this, this.ua),
                touchCollector        : A (this, this.Ha)
            };
            if (window.addEventListener) window.addEventListener ("load",
                    this.c.loadTimeCollector, !1), window.addEventListener ("mousemove", this.c.mouseCollector, !1), window.addEventListener ("keypress", this.c.keyboardCollector, !1), window.addEventListener ("devicemotion", this.c.accelerometerCollector, !1), window.addEventListener ("touchmove", this.c.touchCollector, !1); else if (document.attachEvent) document.attachEvent ("onload", this.c.loadTimeCollector), document.attachEvent ("onmousemove", this.c.mouseCollector), document.attachEvent ("keypress", this.c.keyboardCollector); else throw new sjcl.exception.bug ("can't attach event");
            this.R = !0
        }
    }, stopCollectors     : function () {
        this.R && (window.removeEventListener ? (window.removeEventListener ("load", this.c.loadTimeCollector, !1), window.removeEventListener ("mousemove", this.c.mouseCollector, !1), window.removeEventListener ("keypress", this.c.keyboardCollector, !1), window.removeEventListener ("devicemotion", this.c.accelerometerCollector, !1), window.removeEventListener ("touchmove", this.c.touchCollector, !1)) : document.detachEvent && (document.detachEvent ("onload", this.c.loadTimeCollector), document.detachEvent ("onmousemove",
                this.c.mouseCollector), document.detachEvent ("keypress", this.c.keyboardCollector)), this.R = !1)
    }, addEventListener   : function (a, b) {
        this.X[a][this.wa++] = b
    }, removeEventListener: function (a, b) {
        var c, d, e = this.X[a], f = [];
        for (d in e) e.hasOwnProperty (d) && e[d] === b && f.push (d);
        for (c = 0; c < f.length; c++) d = f[c], delete e[d]
    }, Ca                 : function () {
        B (this, 1)
    }, Fa                 : function (a) {
        var b, c;
        try {
            b = a.x || a.clientX || a.offsetX || 0, c = a.y || a.clientY || a.offsetY || 0
        } catch (d) {
            c = b = 0
        }
        0 != b && 0 != c && this.addEntropy ([b, c], 2, "mouse");
        B (this, 0)
    }, Ha                 : function (a) {
        a =
                a.touches[0] || a.changedTouches[0];
        this.addEntropy ([a.pageX || a.clientX, a.pageY || a.clientY], 1, "touch");
        B (this, 0)
    }, Da                 : function () {
        B (this, 2)
    }, ua                 : function (a) {
        a = a.accelerationIncludingGravity.x || a.accelerationIncludingGravity.y || a.accelerationIncludingGravity.z;
        if (window.orientation) {
            var b = window.orientation;
            "number" === typeof b && this.addEntropy (b, 1, "accelerometer")
        }
        a && this.addEntropy (a, 2, "accelerometer");
        B (this, 0)
    }
};

function z (a, b) {
    var c, d = sjcl.random.X[a], e = [];
    for (c in d) d.hasOwnProperty (c) && e.push (d[c]);
    for (c = 0; c < e.length; c++) e[c] (b)
}

function B (a, b) {
    "undefined" !== typeof window && window.performance && "function" === typeof window.performance.now ? a.addEntropy (window.performance.now (), b, "loadtime") : a.addEntropy ((new Date).valueOf (), b, "loadtime")
}

function u (a) {
    a.f = v (a).concat (v (a));
    a.Y = new sjcl.cipher.aes (a.f)
}

function v (a) {
    for (var b = 0; 4 > b && (a.A[b] = a.A[b] + 1 | 0, !a.A[b]); b++) ;
    return a.Y.encrypt (a.A)
}

function A (a, b) {
    return function () {
        b.apply (a, arguments)
    }
}

sjcl.random = new sjcl.prng (6);
a:try {
    var C, D, E, F;
    if (F = "undefined" !== typeof module && module.exports) {
        var G;
        try {
            G = require ("crypto")
        } catch (a) {
            G = null
        }
        F = D = G
    }
    if (F && D.randomBytes) C = D.randomBytes (128), C = new Uint32Array ((new Uint8Array (C)).buffer), sjcl.random.addEntropy (C, 1024, "crypto['randomBytes']"); else if ("undefined" !== typeof window && "undefined" !== typeof Uint32Array) {
        E = new Uint32Array (32);
        if (window.crypto && window.crypto.getRandomValues) window.crypto.getRandomValues (E); else if (window.msCrypto && window.msCrypto.getRandomValues) window.msCrypto.getRandomValues (E);
        else break a;
        sjcl.random.addEntropy (E, 1024, "crypto['getRandomValues']")
    }
} catch (a) {
    "undefined" !== typeof window && window.console && (console.log ("There was an error collecting entropy from the browser:"), console.log (a))
}
sjcl.json = {
    defaults  : {v: 1, iter: 1E4, ks: 128, ts: 64, mode: "ccm", adata: "", cipher: "aes"}, za: function (a, b, c, d) {
        c = c || {};
        d = d || {};
        var e = sjcl.json, f = e.u ({iv: sjcl.random.randomWords (4, 0)}, e.defaults), g;
        e.u (f, c);
        c = f.adata;
        "string" === typeof f.salt && (f.salt = sjcl.codec.base64.toBits (f.salt));
        "string" === typeof f.iv && (f.iv = sjcl.codec.base64.toBits (f.iv));
        if (!sjcl.mode[f.mode] || !sjcl.cipher[f.cipher] || "string" === typeof a && 100 >= f.iter || 64 !== f.ts && 96 !== f.ts && 128 !== f.ts || 128 !== f.ks && 192 !== f.ks && 0x100 !== f.ks || 2 > f.iv.length ||
                4 < f.iv.length) throw new sjcl.exception.invalid ("json encrypt: invalid parameters");
        "string" === typeof a ? (g = sjcl.misc.cachedPbkdf2 (a, f), a = g.key.slice (0, f.ks / 32), f.salt = g.salt) : sjcl.ecc && a instanceof sjcl.ecc.elGamal.publicKey && (g = a.kem (), f.kemtag = g.tag, a = g.key.slice (0, f.ks / 32));
        "string" === typeof b && (b = sjcl.codec.utf8String.toBits (b));
        "string" === typeof c && (f.adata = c = sjcl.codec.utf8String.toBits (c));
        g = new sjcl.cipher[f.cipher] (a);
        e.u (d, f);
        d.key = a;
        f.ct = "ccm" === f.mode && sjcl.arrayBuffer && sjcl.arrayBuffer.ccm &&
        b instanceof ArrayBuffer ? sjcl.arrayBuffer.ccm.encrypt (g, b, f.iv, c, f.ts) : sjcl.mode[f.mode].encrypt (g, b, f.iv, c, f.ts);
        return f
    }, encrypt: function (a, b, c, d) {
        var e = sjcl.json, f = e.za.apply (e, arguments);
        return e.encode (f)
    }, ya     : function (a, b, c, d) {
        c = c || {};
        d = d || {};
        var e = sjcl.json;
        b = e.u (e.u (e.u ({}, e.defaults), b), c, !0);
        var f, g;
        f = b.adata;
        "string" === typeof b.salt && (b.salt = sjcl.codec.base64.toBits (b.salt));
        "string" === typeof b.iv && (b.iv = sjcl.codec.base64.toBits (b.iv));
        if (!sjcl.mode[b.mode] || !sjcl.cipher[b.cipher] || "string" ===
                typeof a && 100 >= b.iter || 64 !== b.ts && 96 !== b.ts && 128 !== b.ts || 128 !== b.ks && 192 !== b.ks && 0x100 !== b.ks || !b.iv || 2 > b.iv.length || 4 < b.iv.length) throw new sjcl.exception.invalid ("json decrypt: invalid parameters");
        "string" === typeof a ? (g = sjcl.misc.cachedPbkdf2 (a, b), a = g.key.slice (0, b.ks / 32), b.salt = g.salt) : sjcl.ecc && a instanceof sjcl.ecc.elGamal.secretKey && (a = a.unkem (sjcl.codec.base64.toBits (b.kemtag)).slice (0, b.ks / 32));
        "string" === typeof f && (f = sjcl.codec.utf8String.toBits (f));
        g = new sjcl.cipher[b.cipher] (a);
        f = "ccm" ===
        b.mode && sjcl.arrayBuffer && sjcl.arrayBuffer.ccm && b.ct instanceof ArrayBuffer ? sjcl.arrayBuffer.ccm.decrypt (g, b.ct, b.iv, b.tag, f, b.ts) : sjcl.mode[b.mode].decrypt (g, b.ct, b.iv, f, b.ts);
        e.u (d, b);
        d.key = a;
        return 1 === c.raw ? f : sjcl.codec.utf8String.fromBits (f)
    }, decrypt: function (a, b, c, d) {
        var e = sjcl.json;
        return e.ya (a, e.decode (b), c, d)
    }, encode : function (a) {
        var b, c = "{", d = "";
        for (b in a) if (a.hasOwnProperty (b)) {
            if (!b.match (/^[a-z0-9]+$/i)) throw new sjcl.exception.invalid ("json encode: invalid property name");
            c += d + '"' +
                    b + '":';
            d = ",";
            switch (typeof a[b]) {
                case "number":
                case "boolean":
                    c += a[b];
                    break;
                case "string":
                    c += '"' + escape (a[b]) + '"';
                    break;
                case "object":
                    c += '"' + sjcl.codec.base64.fromBits (a[b], 0) + '"';
                    break;
                default:
                    throw new sjcl.exception.bug ("json encode: unsupported type");
            }
        }
        return c + "}"
    }, decode : function (a) {
        a = a.replace (/\s/g, "");
        if (!a.match (/^\{.*\}$/)) throw new sjcl.exception.invalid ("json decode: this isn't json!");
        a = a.replace (/^\{|\}$/g, "").split (/,/);
        var b = {}, c, d;
        for (c = 0; c < a.length; c++) {
            if (!(d = a[c].match (/^\s*(?:(["']?)([a-z][a-z0-9]*)\1)\s*:\s*(?:(-?\d+)|"([a-z0-9+\/%*_.@=\-]*)"|(true|false))$/i))) throw new sjcl.exception.invalid ("json decode: this isn't json!");
            null != d[3] ? b[d[2]] = parseInt (d[3], 10) : null != d[4] ? b[d[2]] = d[2].match (/^(ct|adata|salt|iv)$/) ? sjcl.codec.base64.toBits (d[4]) : unescape (d[4]) : null != d[5] && (b[d[2]] = "true" === d[5])
        }
        return b
    }, u      : function (a, b, c) {
        void 0 === a && (a = {});
        if (void 0 === b) return a;
        for (var d in b) if (b.hasOwnProperty (d)) {
            if (c && void 0 !== a[d] && a[d] !== b[d]) throw new sjcl.exception.invalid ("required parameter overridden");
            a[d] = b[d]
        }
        return a
    }, Ja     : function (a, b) {
        var c = {}, d;
        for (d in a) a.hasOwnProperty (d) && a[d] !== b[d] && (c[d] = a[d]);
        return c
    }, Ia     : function (a,
                          b) {
        var c = {}, d;
        for (d = 0; d < b.length; d++) void 0 !== a[b[d]] && (c[b[d]] = a[b[d]]);
        return c
    }
};
sjcl.encrypt = sjcl.json.encrypt;
sjcl.decrypt = sjcl.json.decrypt;
sjcl.misc.Ga = {};
sjcl.misc.cachedPbkdf2 = function (a, b) {
    var c = sjcl.misc.Ga, d;
    b = b || {};
    d = b.iter || 1E3;
    c = c[a] = c[a] || {};
    d = c[d] = c[d] || {firstSalt: b.salt && b.salt.length ? b.salt.slice (0) : sjcl.random.randomWords (2, 0)};
    c = void 0 === b.salt ? d.firstSalt : b.salt;
    d[c] = d[c] || sjcl.misc.pbkdf2 (a, c, b.iter);
    return {key: d[c].slice (0), salt: c.slice (0)}
};
sjcl.bn = function (a) {
    this.initWith (a)
};
sjcl.bn.prototype = {
    radix           : 24, maxMul: 8, j: sjcl.bn, copy: function () {
        return new this.j (this)
    }, initWith     : function (a) {
        var b = 0, c;
        switch (typeof a) {
            case "object":
                this.limbs = a.limbs.slice (0);
                break;
            case "number":
                this.limbs = [a];
                this.normalize ();
                break;
            case "string":
                a = a.replace (/^0x/, "");
                this.limbs = [];
                c = this.radix / 4;
                for (b = 0; b < a.length; b += c) this.limbs.push (parseInt (a.substring (Math.max (a.length - b - c, 0), a.length - b), 16));
                break;
            default:
                this.limbs = [0]
        }
        return this
    }, equals       : function (a) {
        "number" === typeof a && (a = new this.j (a));
        var b = 0, c;
        this.fullReduce ();
        a.fullReduce ();
        for (c = 0; c < this.limbs.length || c < a.limbs.length; c++) b |= this.getLimb (c) ^ a.getLimb (c);
        return 0 === b
    }, getLimb      : function (a) {
        return a >= this.limbs.length ? 0 : this.limbs[a]
    }, greaterEquals: function (a) {
        "number" === typeof a && (a = new this.j (a));
        var b = 0, c = 0, d, e, f;
        for (d = Math.max (this.limbs.length, a.limbs.length) - 1; 0 <= d; d--) e = this.getLimb (d), f = a.getLimb (d), c |= f - e & ~b, b |= e - f & ~c;
        return (c | ~b) >>> 31
    }, toString     : function () {
        this.fullReduce ();
        var a = "", b, c, d = this.limbs;
        for (b = 0; b < this.limbs.length; b++) {
            for (c =
                         d[b].toString (16); b < this.limbs.length - 1 && 6 > c.length;) c = "0" + c;
            a = c + a
        }
        return "0x" + a
    }, addM         : function (a) {
        "object" !== typeof a && (a = new this.j (a));
        var b = this.limbs, c = a.limbs;
        for (a = b.length; a < c.length; a++) b[a] = 0;
        for (a = 0; a < c.length; a++) b[a] += c[a];
        return this
    }, doubleM      : function () {
        var a, b = 0, c, d = this.radix, e = this.radixMask, f = this.limbs;
        for (a = 0; a < f.length; a++) c = f[a], c = c + c + b, f[a] = c & e, b = c >> d;
        b && f.push (b);
        return this
    }, halveM       : function () {
        var a, b = 0, c, d = this.radix, e = this.limbs;
        for (a = e.length - 1; 0 <= a; a--) c = e[a], e[a] = c + b >>
                1, b = (c & 1) << d;
        e[e.length - 1] || e.pop ();
        return this
    }, subM         : function (a) {
        "object" !== typeof a && (a = new this.j (a));
        var b = this.limbs, c = a.limbs;
        for (a = b.length; a < c.length; a++) b[a] = 0;
        for (a = 0; a < c.length; a++) b[a] -= c[a];
        return this
    }, mod          : function (a) {
        var b = !this.greaterEquals (new sjcl.bn (0));
        a = (new sjcl.bn (a)).normalize ();
        var c = (new sjcl.bn (this)).normalize (), d = 0;
        for (b && (c = (new sjcl.bn (0)).subM (c).normalize ()); c.greaterEquals (a); d++) a.doubleM ();
        for (b && (c = a.sub (c).normalize ()); 0 < d; d--) a.halveM (), c.greaterEquals (a) &&
        c.subM (a).normalize ();
        return c.trim ()
    }, inverseMod   : function (a) {
        var b = new sjcl.bn (1), c = new sjcl.bn (0), d = new sjcl.bn (this), e = new sjcl.bn (a), f, g = 1;
        if (!(a.limbs[0] & 1)) throw new sjcl.exception.invalid ("inverseMod: p must be odd");
        do for (d.limbs[0] & 1 && (d.greaterEquals (e) || (f = d, d = e, e = f, f = b, b = c, c = f), d.subM (e), d.normalize (), b.greaterEquals (c) || b.addM (a), b.subM (c)), d.halveM (), b.limbs[0] & 1 && b.addM (a), b.normalize (), b.halveM (), f = g = 0; f < d.limbs.length; f++) g |= d.limbs[f]; while (g);
        if (!e.equals (1)) throw new sjcl.exception.invalid ("inverseMod: p and x must be relatively prime");
        return c
    }, add          : function (a) {
        return this.copy ().addM (a)
    }, sub          : function (a) {
        return this.copy ().subM (a)
    }, mul          : function (a) {
        "number" === typeof a && (a = new this.j (a));
        var b, c = this.limbs, d = a.limbs, e = c.length, f = d.length, g = new this.j, h = g.limbs, k, l = this.maxMul;
        for (b = 0; b < this.limbs.length + a.limbs.length + 1; b++) h[b] = 0;
        for (b = 0; b < e; b++) {
            k = c[b];
            for (a = 0; a < f; a++) h[b + a] += k * d[a];
            --l || (l = this.maxMul, g.cnormalize ())
        }
        return g.cnormalize ().reduce ()
    }, square       : function () {
        return this.mul (this)
    }, power        : function (a) {
        a = (new sjcl.bn (a)).normalize ().trim ().limbs;
        var b, c, d = new this.j (1), e = this;
        for (b = 0; b < a.length; b++) for (c = 0; c < this.radix; c++) {
            a[b] & 1 << c && (d = d.mul (e));
            if (b == a.length - 1 && 0 == a[b] >> c + 1) break;
            e = e.square ()
        }
        return d
    }, mulmod       : function (a, b) {
        return this.mod (b).mul (a.mod (b)).mod (b)
    }, powermod     : function (a, b) {
        a = new sjcl.bn (a);
        b = new sjcl.bn (b);
        if (1 == (b.limbs[0] & 1)) {
            var c = this.montpowermod (a, b);
            if (0 != c) return c
        }
        for (var d, e = a.normalize ().trim ().limbs, f = new this.j (1), g = this, c = 0; c < e.length; c++) for (d = 0; d < this.radix; d++) {
            e[c] & 1 << d && (f = f.mulmod (g, b));
            if (c == e.length -
                    1 && 0 == e[c] >> d + 1) break;
            g = g.mulmod (g, b)
        }
        return f
    }, montpowermod : function (a, b) {
        function c (a, b) {
            var c = b % a.radix;
            return (a.limbs[Math.floor (b / a.radix)] & 1 << c) >> c
        }

        function d (a, c) {
            var d, e, f = (1 << l + 1) - 1;
            d = a.mul (c);
            e = d.mul (r);
            e.limbs = e.limbs.slice (0, k.limbs.length);
            e.limbs.length == k.limbs.length && (e.limbs[k.limbs.length - 1] &= f);
            e = e.mul (b);
            e = d.add (e).normalize ().trim ();
            e.limbs = e.limbs.slice (k.limbs.length - 1);
            for (d = 0; d < e.limbs.length; d++) 0 < d && (e.limbs[d - 1] |= (e.limbs[d] & f) << g - l - 1), e.limbs[d] >>= l + 1;
            e.greaterEquals (b) &&
            e.subM (b);
            return e
        }

        a = (new sjcl.bn (a)).normalize ().trim ();
        b = new sjcl.bn (b);
        var e, f, g = this.radix, h = new this.j (1);
        e = this.copy ();
        var k, l, m;
        m = a.bitLength ();
        k = new sjcl.bn ({
            limbs: b.copy ().normalize ().trim ().limbs.map (function () {
                return 0
            })
        });
        for (l = this.radix; 0 < l; l--) if (1 == (b.limbs[b.limbs.length - 1] >> l & 1)) {
            k.limbs[k.limbs.length - 1] = 1 << l;
            break
        }
        if (0 == m) return this;
        m = 18 > m ? 1 : 48 > m ? 3 : 144 > m ? 4 : 768 > m ? 5 : 6;
        var n = k.copy (), p = b.copy ();
        f = new sjcl.bn (1);
        for (var r = new sjcl.bn (0), q = k.copy (); q.greaterEquals (1);) q.halveM (), 0 ==
        (f.limbs[0] & 1) ? (f.halveM (), r.halveM ()) : (f.addM (p), f.halveM (), r.halveM (), r.addM (n));
        f = f.normalize ();
        r = r.normalize ();
        n.doubleM ();
        p = n.mulmod (n, b);
        if (!n.mul (f).sub (b.mul (r)).equals (1)) return !1;
        e = d (e, p);
        h = d (h, p);
        n = {};
        f = (1 << m - 1) - 1;
        n[1] = e.copy ();
        n[2] = d (e, e);
        for (e = 1; e <= f; e++) n[2 * e + 1] = d (n[2 * e - 1], n[2]);
        for (e = a.bitLength () - 1; 0 <= e;) if (0 == c (a, e)) h = d (h, h), --e; else {
            for (p = e - m + 1; 0 == c (a, p);) p++;
            q = 0;
            for (f = p; f <= e; f++) q += c (a, f) << f - p, h = d (h, h);
            h = d (h, n[q]);
            e = p - 1
        }
        return d (h, 1)
    }, trim         : function () {
        var a = this.limbs, b;
        do b = a.pop ();
        while (a.length && 0 === b);
        a.push (b);
        return this
    }, reduce       : function () {
        return this
    }, fullReduce   : function () {
        return this.normalize ()
    }, normalize    : function () {
        var a = 0, b, c = this.placeVal, d = this.ipv, e, f = this.limbs, g = f.length, h = this.radixMask;
        for (b = 0; b < g || 0 !== a && -1 !== a; b++) a = (f[b] || 0) + a, e = f[b] = a & h, a = (a - e) * d;
        -1 === a && (f[b - 1] -= c);
        this.trim ();
        return this
    }, cnormalize   : function () {
        var a = 0, b, c = this.ipv, d, e = this.limbs, f = e.length, g = this.radixMask;
        for (b = 0; b < f - 1; b++) a = e[b] + a, d = e[b] = a & g, a = (a - d) * c;
        e[b] += a;
        return this
    }, toBits       : function (a) {
        this.fullReduce ();
        a = a || this.exponent || this.bitLength ();
        var b = Math.floor ((a - 1) / 24), c = sjcl.bitArray,
                d = [c.partial ((a + 7 & -8) % this.radix || this.radix, this.getLimb (b))];
        for (b--; 0 <= b; b--) d = c.concat (d, [c.partial (Math.min (this.radix, a), this.getLimb (b))]), a -= this.radix;
        return d
    }, bitLength    : function () {
        this.fullReduce ();
        for (var a = this.radix * (this.limbs.length - 1), b = this.limbs[this.limbs.length - 1]; b; b >>>= 1) a++;
        return a + 7 & -8
    }
};
sjcl.bn.fromBits = function (a) {
    var b = new this, c = [], d = sjcl.bitArray, e = this.prototype,
            f = Math.min (this.bitLength || 0x100000000, d.bitLength (a)), g = f % e.radix || e.radix;
    for (c[0] = d.extract (a, 0, g); g < f; g += e.radix) c.unshift (d.extract (a, g, e.radix));
    b.limbs = c;
    return b
};
sjcl.bn.prototype.ipv = 1 / (sjcl.bn.prototype.placeVal = Math.pow (2, sjcl.bn.prototype.radix));
sjcl.bn.prototype.radixMask = (1 << sjcl.bn.prototype.radix) - 1;
sjcl.bn.pseudoMersennePrime = function (a, b) {
    function c (a) {
        this.initWith (a)
    }

    var d = c.prototype = new sjcl.bn, e, f;
    e = d.modOffset = Math.ceil (f = a / d.radix);
    d.exponent = a;
    d.offset = [];
    d.factor = [];
    d.minOffset = e;
    d.fullMask = 0;
    d.fullOffset = [];
    d.fullFactor = [];
    d.modulus = c.modulus = new sjcl.bn (Math.pow (2, a));
    d.fullMask = 0 | -Math.pow (2, a % d.radix);
    for (e = 0; e < b.length; e++) d.offset[e] = Math.floor (b[e][0] / d.radix - f), d.fullOffset[e] = Math.ceil (b[e][0] / d.radix - f), d.factor[e] = b[e][1] * Math.pow (.5, a - b[e][0] + d.offset[e] * d.radix), d.fullFactor[e] =
            b[e][1] * Math.pow (.5, a - b[e][0] + d.fullOffset[e] * d.radix), d.modulus.addM (new sjcl.bn (Math.pow (2, b[e][0]) * b[e][1])), d.minOffset = Math.min (d.minOffset, -d.offset[e]);
    d.j = c;
    d.modulus.cnormalize ();
    d.reduce = function () {
        var a, b, c, d = this.modOffset, e = this.limbs, f = this.offset, p = this.offset.length, r = this.factor, q;
        for (a = this.minOffset; e.length > d;) {
            c = e.pop ();
            q = e.length;
            for (b = 0; b < p; b++) e[q + f[b]] -= r[b] * c;
            a--;
            a || (e.push (0), this.cnormalize (), a = this.minOffset)
        }
        this.cnormalize ();
        return this
    };
    d.pa = -1 === d.fullMask ? d.reduce :
            function () {
                var a = this.limbs, b = a.length - 1, c, d;
                this.reduce ();
                if (b === this.modOffset - 1) {
                    d = a[b] & this.fullMask;
                    a[b] -= d;
                    for (c = 0; c < this.fullOffset.length; c++) a[b + this.fullOffset[c]] -= this.fullFactor[c] * d;
                    this.normalize ()
                }
            };
    d.fullReduce = function () {
        var a, b;
        this.pa ();
        this.addM (this.modulus);
        this.addM (this.modulus);
        this.normalize ();
        this.pa ();
        for (b = this.limbs.length; b < this.modOffset; b++) this.limbs[b] = 0;
        a = this.greaterEquals (this.modulus);
        for (b = 0; b < this.limbs.length; b++) this.limbs[b] -= this.modulus.limbs[b] * a;
        this.cnormalize ();
        return this
    };
    d.inverse = function () {
        return this.power (this.modulus.sub (2))
    };
    c.fromBits = sjcl.bn.fromBits;
    return c
};
var H = sjcl.bn.pseudoMersennePrime;
sjcl.bn.prime = {
    p127  : H (127, [[0, -1]]),
    p25519: H (255, [[0, -19]]),
    p192k : H (192, [[32, -1], [12, -1], [8, -1], [7, -1], [6, -1], [3, -1], [0, -1]]),
    p224k : H (224, [[32, -1], [12, -1], [11, -1], [9, -1], [7, -1], [4, -1], [1, -1], [0, -1]]),
    p256k : H (0x100, [[32, -1], [9, -1], [8, -1], [7, -1], [6, -1], [4, -1], [0, -1]]),
    p192  : H (192, [[0, -1], [64, -1]]),
    p224  : H (224, [[0, 1], [96, -1]]),
    p256  : H (0x100, [[0, -1], [96, 1], [192, 1], [224, -1]]),
    p384  : H (384, [[0, -1], [32, 1], [96, -1], [128, -1]]),
    p521  : H (521, [[0, -1]])
};
sjcl.bn.random = function (a, b) {
    "object" !== typeof a && (a = new sjcl.bn (a));
    for (var c, d, e = a.limbs.length, f = a.limbs[e - 1] + 1, g = new sjcl.bn; ;) {
        do c = sjcl.random.randomWords (e, b), 0 > c[e - 1] && (c[e - 1] += 0x100000000); while (Math.floor (c[e - 1] / f) === Math.floor (0x100000000 / f));
        c[e - 1] %= f;
        for (d = 0; d < e - 1; d++) c[d] &= a.radixMask;
        g.limbs = c;
        if (!g.greaterEquals (a)) return g
    }
};
sjcl.ecc = {};
sjcl.ecc.point = function (a, b, c) {
    void 0 === b ? this.isIdentity = !0 : (b instanceof sjcl.bn && (b = new a.field (b)), c instanceof sjcl.bn && (c = new a.field (c)), this.x = b, this.y = c, this.isIdentity = !1);
    this.curve = a
};
sjcl.ecc.point.prototype = {
    toJac       : function () {
        return new sjcl.ecc.pointJac (this.curve, this.x, this.y, new this.curve.field (1))
    }, mult     : function (a) {
        return this.toJac ().mult (a, this).toAffine ()
    }, mult2    : function (a, b, c) {
        return this.toJac ().mult2 (a, this, b, c).toAffine ()
    }, multiples: function () {
        var a, b, c;
        if (void 0 === this.ma) for (c = this.toJac ().doubl (), a = this.ma = [new sjcl.ecc.point (this.curve), this, c.toAffine ()], b = 3; 16 > b; b++) c = c.add (this), a.push (c.toAffine ());
        return this.ma
    }, negate   : function () {
        var a = (new this.curve.field (0)).sub (this.y).normalize ().reduce ();
        return new sjcl.ecc.point (this.curve, this.x, a)
    }, isValid  : function () {
        return this.y.square ().equals (this.curve.b.add (this.x.mul (this.curve.a.add (this.x.square ()))))
    }, toBits   : function () {
        return sjcl.bitArray.concat (this.x.toBits (), this.y.toBits ())
    }
};
sjcl.ecc.pointJac = function (a, b, c, d) {
    void 0 === b ? this.isIdentity = !0 : (this.x = b, this.y = c, this.z = d, this.isIdentity = !1);
    this.curve = a
};
sjcl.ecc.pointJac.prototype = {
    add        : function (a) {
        var b, c, d, e;
        if (this.curve !== a.curve) throw new sjcl.exception.invalid ("sjcl['ecc']['add'](): Points must be on the same curve to add them!");
        if (this.isIdentity) return a.toJac ();
        if (a.isIdentity) return this;
        b = this.z.square ();
        c = a.x.mul (b).subM (this.x);
        if (c.equals (0)) return this.y.equals (a.y.mul (b.mul (this.z))) ? this.doubl () : new sjcl.ecc.pointJac (this.curve);
        b = a.y.mul (b.mul (this.z)).subM (this.y);
        d = c.square ();
        a = b.square ();
        e = c.square ().mul (c).addM (this.x.add (this.x).mul (d));
        a = a.subM (e);
        b = this.x.mul (d).subM (a).mul (b);
        d = this.y.mul (c.square ().mul (c));
        b = b.subM (d);
        c = this.z.mul (c);
        return new sjcl.ecc.pointJac (this.curve, a, b, c)
    }, doubl   : function () {
        if (this.isIdentity) return this;
        var a = this.y.square (), b = a.mul (this.x.mul (4)), c = a.square ().mul (8), a = this.z.square (),
                d = this.curve.a.toString () == (new sjcl.bn (-3)).toString () ? this.x.sub (a).mul (3).mul (this.x.add (a)) : this.x.square ().mul (3).add (a.square ().mul (this.curve.a)),
                a = d.square ().subM (b).subM (b), b = b.sub (a).mul (d).subM (c), c = this.y.add (this.y).mul (this.z);
        return new sjcl.ecc.pointJac (this.curve, a, b, c)
    }, toAffine: function () {
        if (this.isIdentity || this.z.equals (0)) return new sjcl.ecc.point (this.curve);
        var a = this.z.inverse (), b = a.square ();
        return new sjcl.ecc.point (this.curve, this.x.mul (b).fullReduce (), this.y.mul (b.mul (a)).fullReduce ())
    }, mult    : function (a, b) {
        "number" === typeof a ? a = [a] : void 0 !== a.limbs && (a = a.normalize ().limbs);
        var c, d, e = (new sjcl.ecc.point (this.curve)).toJac (), f = b.multiples ();
        for (c = a.length - 1; 0 <= c; c--) for (d = sjcl.bn.prototype.radix - 4; 0 <= d; d -= 4) e =
                e.doubl ().doubl ().doubl ().doubl ().add (f[a[c] >> d & 15]);
        return e
    }, mult2   : function (a, b, c, d) {
        "number" === typeof a ? a = [a] : void 0 !== a.limbs && (a = a.normalize ().limbs);
        "number" === typeof c ? c = [c] : void 0 !== c.limbs && (c = c.normalize ().limbs);
        var e, f = (new sjcl.ecc.point (this.curve)).toJac ();
        b = b.multiples ();
        var g = d.multiples (), h, k;
        for (d = Math.max (a.length, c.length) - 1; 0 <= d; d--) for (h = a[d] | 0, k = c[d] | 0, e = sjcl.bn.prototype.radix - 4; 0 <= e; e -= 4) f = f.doubl ().doubl ().doubl ().doubl ().add (b[h >> e & 15]).add (g[k >> e & 15]);
        return f
    }, negate  : function () {
        return this.toAffine ().negate ().toJac ()
    },
    isValid    : function () {
        var a = this.z.square (), b = a.square (), a = b.mul (a);
        return this.y.square ().equals (this.curve.b.mul (a).add (this.x.mul (this.curve.a.mul (b).add (this.x.square ()))))
    }
};
sjcl.ecc.curve = function (a, b, c, d, e, f) {
    this.field = a;
    this.r = new sjcl.bn (b);
    this.a = new a (c);
    this.b = new a (d);
    this.G = new sjcl.ecc.point (this, new a (e), new a (f))
};
sjcl.ecc.curve.prototype.fromBits = function (a) {
    var b = sjcl.bitArray, c = this.field.prototype.exponent + 7 & -8;
    a = new sjcl.ecc.point (this, this.field.fromBits (b.bitSlice (a, 0, c)), this.field.fromBits (b.bitSlice (a, c, 2 * c)));
    if (!a.isValid ()) throw new sjcl.exception.corrupt ("not on the curve!");
    return a
};
sjcl.ecc.curves = {
    c192: new sjcl.ecc.curve (sjcl.bn.prime.p192, "0xffffffffffffffffffffffff99def836146bc9b1b4d22831", -3, "0x64210519e59c80e70fa7e9ab72243049feb8deecc146b9b1", "0x188da80eb03090f67cbf20eb43a18800f4ff0afd82ff1012", "0x07192b95ffc8da78631011ed6b24cdd573f977a11e794811"),
    c224: new sjcl.ecc.curve (sjcl.bn.prime.p224, "0xffffffffffffffffffffffffffff16a2e0b8f03e13dd29455c5c2a3d", -3, "0xb4050a850c04b3abf54132565044b0b7d7bfd8ba270b39432355ffb4", "0xb70e0cbd6bb4bf7f321390b94a03c1d356c21122343280d6115c1d21",
            "0xbd376388b5f723fb4c22dfe6cd4375a05a07476444d5819985007e34"),
    c256: new sjcl.ecc.curve (sjcl.bn.prime.p256, "0xffffffff00000000ffffffffffffffffbce6faada7179e84f3b9cac2fc632551", -3, "0x5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "0x6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "0x4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5"),
    c384: new sjcl.ecc.curve (sjcl.bn.prime.p384, "0xffffffffffffffffffffffffffffffffffffffffffffffffc7634d81f4372ddf581a0db248b0a77aecec196accc52973",
            -3, "0xb3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "0xaa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "0x3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f"),
    c521: new sjcl.ecc.curve (sjcl.bn.prime.p521, "0x1FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409", -3, "0x051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00",
            "0xC6858E06B70404E9CD9E3ECB662395B4429C648139053FB521F828AF606B4D3DBAA14B5E77EFE75928FE1DC127A2FFA8DE3348B3C1856A429BF97E7E31C2E5BD66", "0x11839296A789A3BC0045C8A5FB42C7D1BD998F54449579B446817AFBD17273E662C97EE72995EF42640C550B9013FAD0761353C7086A272C24088BE94769FD16650"),
    k192: new sjcl.ecc.curve (sjcl.bn.prime.p192k, "0xfffffffffffffffffffffffe26f2fc170f69466a74defd8d", 0, 3, "0xdb4ff10ec057e9ae26b07d0280b7f4341da5d1b1eae06c7d", "0x9b2f2f6d9c5628a7844163d015be86344082aa88d95e2f9d"),
    k224: new sjcl.ecc.curve (sjcl.bn.prime.p224k,
            "0x010000000000000000000000000001dce8d2ec6184caf0a971769fb1f7", 0, 5, "0xa1455b334df099df30fc28a169a467e9e47075a90f7e650eb6b7a45c", "0x7e089fed7fba344282cafbd6f7e319f7c0b0bd59e2ca4bdb556d61a5"),
    k256: new sjcl.ecc.curve (sjcl.bn.prime.p256k, "0xfffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 0, 7, "0x79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", "0x483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8")
};
sjcl.ecc.curveName = function (a) {
    for (var b in sjcl.ecc.curves) if (sjcl.ecc.curves.hasOwnProperty (b) && sjcl.ecc.curves[b] === a) return b;
    throw new sjcl.exception.invalid ("no such curve");
};
sjcl.ecc.deserialize = function (a) {
    if (!a || !a.curve || !sjcl.ecc.curves[a.curve]) throw new sjcl.exception.invalid ("invalid serialization");
    if (-1 === ["elGamal", "ecdsa"].indexOf (a.type)) throw new sjcl.exception.invalid ("invalid type");
    var b = sjcl.ecc.curves[a.curve];
    if (a.secretKey) {
        if (!a.exponent) throw new sjcl.exception.invalid ("invalid exponent");
        var c = new sjcl.bn (a.exponent);
        return new sjcl.ecc[a.type].secretKey (b, c)
    }
    if (!a.point) throw new sjcl.exception.invalid ("invalid point");
    c = b.fromBits (sjcl.codec.hex.toBits (a.point));
    return new sjcl.ecc[a.type].publicKey (b, c)
};
sjcl.ecc.basicKey = {
    publicKey   : function (a, b) {
        this.o = a;
        this.D = a.r.bitLength ();
        b instanceof Array ? this.B = a.fromBits (b) : this.B = b;
        this.serialize = function () {
            var b = sjcl.ecc.curveName (a);
            return {type: this.getType (), secretKey: !1, point: sjcl.codec.hex.fromBits (this.B.toBits ()), curve: b}
        };
        this.get = function () {
            var a = this.B.toBits (), b = sjcl.bitArray.bitLength (a), e = sjcl.bitArray.bitSlice (a, 0, b / 2),
                    a = sjcl.bitArray.bitSlice (a, b / 2);
            return {x: e, y: a}
        }
    }, secretKey: function (a, b) {
        this.o = a;
        this.D = a.r.bitLength ();
        this.P = b;
        this.serialize =
                function () {
                    var b = this.get (), d = sjcl.ecc.curveName (a);
                    return {type: this.getType (), secretKey: !0, exponent: sjcl.codec.hex.fromBits (b), curve: d}
                };
        this.get = function () {
            return this.P.toBits ()
        }
    }
};
sjcl.ecc.basicKey.generateKeys = function (a) {
    return function (b, c, d) {
        b = b || 0x100;
        if ("number" === typeof b && (b = sjcl.ecc.curves["c" + b], void 0 === b)) throw new sjcl.exception.invalid ("no such curve");
        d = d || sjcl.bn.random (b.r, c);
        c = b.G.mult (d);
        return {pub: new sjcl.ecc[a].publicKey (b, c), sec: new sjcl.ecc[a].secretKey (b, d)}
    }
};
sjcl.ecc.elGamal = {
    generateKeys: sjcl.ecc.basicKey.generateKeys ("elGamal"), publicKey: function (a, b) {
        sjcl.ecc.basicKey.publicKey.apply (this, arguments)
    }, secretKey: function (a, b) {
        sjcl.ecc.basicKey.secretKey.apply (this, arguments)
    }
};
sjcl.ecc.elGamal.publicKey.prototype = {
    kem       : function (a) {
        a = sjcl.bn.random (this.o.r, a);
        var b = this.o.G.mult (a).toBits ();
        return {key: sjcl.hash.sha256.hash (this.B.mult (a).toBits ()), tag: b}
    }, getType: function () {
        return "elGamal"
    }
};
sjcl.ecc.elGamal.secretKey.prototype = {
    unkem      : function (a) {
        return sjcl.hash.sha256.hash (this.o.fromBits (a).mult (this.P).toBits ())
    }, dh      : function (a) {
        return sjcl.hash.sha256.hash (a.B.mult (this.P).toBits ())
    }, dhJavaEc: function (a) {
        return a.B.mult (this.P).x.toBits ()
    }, getType : function () {
        return "elGamal"
    }
};
sjcl.ecc.ecdsa = {generateKeys: sjcl.ecc.basicKey.generateKeys ("ecdsa")};
sjcl.ecc.ecdsa.publicKey = function (a, b) {
    sjcl.ecc.basicKey.publicKey.apply (this, arguments)
};
sjcl.ecc.ecdsa.publicKey.prototype = {
    verify    : function (a, b, c) {
        sjcl.bitArray.bitLength (a) > this.D && (a = sjcl.bitArray.clamp (a, this.D));
        var d = sjcl.bitArray, e = this.o.r, f = this.D, g = sjcl.bn.fromBits (d.bitSlice (b, 0, f)),
                d = sjcl.bn.fromBits (d.bitSlice (b, f, 2 * f)), h = c ? d : d.inverseMod (e),
                f = sjcl.bn.fromBits (a).mul (h).mod (e), h = g.mul (h).mod (e), f = this.o.G.mult2 (f, h, this.B).x;
        if (g.equals (0) || d.equals (0) || g.greaterEquals (e) || d.greaterEquals (e) || !f.equals (g)) {
            if (void 0 === c) return this.verify (a, b, !0);
            throw new sjcl.exception.corrupt ("signature didn't check out");
        }
        return !0
    }, getType: function () {
        return "ecdsa"
    }
};
sjcl.ecc.ecdsa.secretKey = function (a, b) {
    sjcl.ecc.basicKey.secretKey.apply (this, arguments)
};
sjcl.ecc.ecdsa.secretKey.prototype = {
    sign      : function (a, b, c, d) {
        sjcl.bitArray.bitLength (a) > this.D && (a = sjcl.bitArray.clamp (a, this.D));
        var e = this.o.r, f = e.bitLength ();
        d = d || sjcl.bn.random (e.sub (1), b).add (1);
        b = this.o.G.mult (d).x.mod (e);
        a = sjcl.bn.fromBits (a).add (b.mul (this.P));
        c = c ? a.inverseMod (e).mul (d).mod (e) : a.mul (d.inverseMod (e)).mod (e);
        return sjcl.bitArray.concat (b.toBits (f), c.toBits (f))
    }, getType: function () {
        return "ecdsa"
    }
};
sjcl.keyexchange.srp = {
    makeVerifier : function (a, b, c, d) {
        a = sjcl.keyexchange.srp.makeX (a, b, c);
        a = sjcl.bn.fromBits (a);
        return d.g.powermod (a, d.N)
    }, makeX     : function (a, b, c) {
        a = sjcl.hash.sha1.hash (a + ":" + b);
        return sjcl.hash.sha1.hash (sjcl.bitArray.concat (c, a))
    }, knownGroup: function (a) {
        "string" !== typeof a && (a = a.toString ());
        sjcl.keyexchange.srp.ha || sjcl.keyexchange.srp.Ba ();
        return sjcl.keyexchange.srp.la[a]
    }, ha        : !1, Ba: function () {
        var a, b;
        for (a = 0; a < sjcl.keyexchange.srp.ka.length; a++) b = sjcl.keyexchange.srp.ka[a].toString (),
                b = sjcl.keyexchange.srp.la[b], b.N = new sjcl.bn (b.N), b.g = new sjcl.bn (b.g);
        sjcl.keyexchange.srp.ha = !0
    }, ka        : [1024, 1536, 2048, 3072, 0x1000, 6144, 8192], la: {
        1024     : {
            N: "EEAF0AB9ADB38DD69C33F80AFA8FC5E86072618775FF3C0B9EA2314C9C256576D674DF7496EA81D3383B4813D692C6E0E0D5D8E250B98BE48E495C1D6089DAD15DC7D7B46154D6B6CE8EF4AD69B15D4982559B297BCF1885C529F566660E57EC68EDBC3C05726CC02FD4CBF4976EAA9AFD5138FE8376435B9FC61D2FC0EB06E3",
            g: 2
        }, 1536  : {
            N: "9DEF3CAFB939277AB1F12A8617A47BBBDBA51DF499AC4C80BEEEA9614B19CC4D5F4F5F556E27CBDE51C6A94BE4607A291558903BA0D0F84380B655BB9A22E8DCDF028A7CEC67F0D08134B1C8B97989149B609E0BE3BAB63D47548381DBC5B1FC764E3F4B53DD9DA1158BFD3E2B9C8CF56EDF019539349627DB2FD53D24B7C48665772E437D6C7F8CE442734AF7CCB7AE837C264AE3A9BEB87F8A2FE9B8B5292E5A021FFF5E91479E8CE7A28C2442C6F315180F93499A234DCF76E3FED135F9BB",
            g: 2
        }, 2048  : {
            N: "AC6BDB41324A9A9BF166DE5E1389582FAF72B6651987EE07FC3192943DB56050A37329CBB4A099ED8193E0757767A13DD52312AB4B03310DCD7F48A9DA04FD50E8083969EDB767B0CF6095179A163AB3661A05FBD5FAAAE82918A9962F0B93B855F97993EC975EEAA80D740ADBF4FF747359D041D5C33EA71D281E446B14773BCA97B43A23FB801676BD207A436C6481F1D2B9078717461A5B9D32E688F87748544523B524B0D57D5EA77A2775D2ECFA032CFBDBF52FB3786160279004E57AE6AF874E7303CE53299CCC041C7BC308D82A5698F3A8D0C38271AE35F8E9DBFBB694B5C803D89F7AE435DE236D525F54759B65E372FCD68EF20FA7111F9E4AFF73",
            g: 2
        }, 3072  : {
            N: "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E208E24FA074E5AB3143DB5BFCE0FD108E4B82D120A93AD2CAFFFFFFFFFFFFFFFF",
            g: 5
        }, 0x1000: {
            N: "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E208E24FA074E5AB3143DB5BFCE0FD108E4B82D120A92108011A723C12A787E6D788719A10BDBA5B2699C327186AF4E23C1A946834B6150BDA2583E9CA2AD44CE8DBBBC2DB04DE8EF92E8EFC141FBECAA6287C59474E6BC05D99B2964FA090C3A2233BA186515BE7ED1F612970CEE2D7AFB81BDD762170481CD0069127D5B05AA993B4EA988D8FDDC186FFB7DC90A6C08F4DF435C934063199FFFFFFFFFFFFFFFF",
            g: 5
        }, 6144  : {
            N: "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E208E24FA074E5AB3143DB5BFCE0FD108E4B82D120A92108011A723C12A787E6D788719A10BDBA5B2699C327186AF4E23C1A946834B6150BDA2583E9CA2AD44CE8DBBBC2DB04DE8EF92E8EFC141FBECAA6287C59474E6BC05D99B2964FA090C3A2233BA186515BE7ED1F612970CEE2D7AFB81BDD762170481CD0069127D5B05AA993B4EA988D8FDDC186FFB7DC90A6C08F4DF435C93402849236C3FAB4D27C7026C1D4DCB2602646DEC9751E763DBA37BDF8FF9406AD9E530EE5DB382F413001AEB06A53ED9027D831179727B0865A8918DA3EDBEBCF9B14ED44CE6CBACED4BB1BDB7F1447E6CC254B332051512BD7AF426FB8F401378CD2BF5983CA01C64B92ECF032EA15D1721D03F482D7CE6E74FEF6D55E702F46980C82B5A84031900B1C9E59E7C97FBEC7E8F323A97A7E36CC88BE0F1D45B7FF585AC54BD407B22B4154AACC8F6D7EBF48E1D814CC5ED20F8037E0A79715EEF29BE32806A1D58BB7C5DA76F550AA3D8A1FBFF0EB19CCB1A313D55CDA56C9EC2EF29632387FE8D76E3C0468043E8F663F4860EE12BF2D5B0B7474D6E694F91E6DCC4024FFFFFFFFFFFFFFFF",
            g: 5
        }, 8192  : {
            N: "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6BF12FFA06D98A0864D87602733EC86A64521F2B18177B200CBBE117577A615D6C770988C0BAD946E208E24FA074E5AB3143DB5BFCE0FD108E4B82D120A92108011A723C12A787E6D788719A10BDBA5B2699C327186AF4E23C1A946834B6150BDA2583E9CA2AD44CE8DBBBC2DB04DE8EF92E8EFC141FBECAA6287C59474E6BC05D99B2964FA090C3A2233BA186515BE7ED1F612970CEE2D7AFB81BDD762170481CD0069127D5B05AA993B4EA988D8FDDC186FFB7DC90A6C08F4DF435C93402849236C3FAB4D27C7026C1D4DCB2602646DEC9751E763DBA37BDF8FF9406AD9E530EE5DB382F413001AEB06A53ED9027D831179727B0865A8918DA3EDBEBCF9B14ED44CE6CBACED4BB1BDB7F1447E6CC254B332051512BD7AF426FB8F401378CD2BF5983CA01C64B92ECF032EA15D1721D03F482D7CE6E74FEF6D55E702F46980C82B5A84031900B1C9E59E7C97FBEC7E8F323A97A7E36CC88BE0F1D45B7FF585AC54BD407B22B4154AACC8F6D7EBF48E1D814CC5ED20F8037E0A79715EEF29BE32806A1D58BB7C5DA76F550AA3D8A1FBFF0EB19CCB1A313D55CDA56C9EC2EF29632387FE8D76E3C0468043E8F663F4860EE12BF2D5B0B7474D6E694F91E6DBE115974A3926F12FEE5E438777CB6A932DF8CD8BEC4D073B931BA3BC832B68D9DD300741FA7BF8AFC47ED2576F6936BA424663AAB639C5AE4F5683423B4742BF1C978238F16CBE39D652DE3FDB8BEFC848AD922222E04A4037C0713EB57A81A23F0C73473FC646CEA306B4BCBC8862F8385DDFA9D4B7FA2C087E879683303ED5BDD3A062B3CF5B3A278A66D2A13F83F44F82DDF310EE074AB6A364597E899A0255DC164F31CC50846851DF9AB48195DED7EA1B1D510BD7EE74D73FAF36BC31ECFA268359046F4EB879F924009438B481C6CD7889A002ED5EE382BC9190DA6FC026E479558E4475677E9AA9E3050E2765694DFC81F56E880B96E7160C980DD98EDD3DFFFFFFFFFFFFFFFFF",
            g: 19
        }
    }
};
"undefined" !== typeof module && module.exports && (module.exports = sjcl);
"function" === typeof define && define ([], function () {
    return sjcl
});
