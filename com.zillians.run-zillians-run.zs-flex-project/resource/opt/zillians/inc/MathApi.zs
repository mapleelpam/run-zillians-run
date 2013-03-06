/**
 * Zillians MMO
 * Copyright (C) 2007-2010 Zillians.com, Inc.
 * For more information see http://www.zillians.com
 *
 * Zillians MMO is the library and runtime for massive multiplayer online game
 * development in utility computing model, which runs as a service for every
 * developer to build their virtual world running on our GPU-assisted machines.
 *
 * This is a close source library intended to be used solely within Zillians.com
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER(S) BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * @date May 17, 2010 sdk - Initial version created.
 */

package zillians.api.math
{
	@function_mapper { name="__zillians_api_math_abs"; }
	function abs(var x:int32):int32;
	@function_mapper { name="__zillians_api_math_labs"; }
	function labs(var x:int64):int64;
	@function_mapper { name="__zillians_api_math_fabs"; }
	function fabs(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_fabsf"; }
	function fabsf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_min"; }
	function min(var x:int32, var y:int32):int32;
	@function_mapper { name="__zillians_api_math_umin"; }
	function umin(var x:uint32, var y:uint32):uint32;
	@function_mapper { name="__zillians_api_math_lmin"; }
	function lmin(var x:int64, var y:int64):int64;
	@function_mapper { name="__zillians_api_math_lumin"; }
	function lumin(var x:uint64, var y:uint64):uint64;
	@function_mapper { name="__zillians_api_math_fmin"; }
	function fmin(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_fminf"; }
	function fminf(var x:float32, var y:float32):float32;

	@function_mapper { name="__zillians_api_math_max"; }
	function max(var x:int32, var y:int32):int32;
	@function_mapper { name="__zillians_api_math_umax"; }
	function umax(var x:uint32, var y:uint32):uint32;
	@function_mapper { name="__zillians_api_math_lmax"; }
	function lmax(var x:int64, var y:int64):int64;
	@function_mapper { name="__zillians_api_math_lumax"; }
	function lumax(var x:uint64, var y:uint64):uint64;
	@function_mapper { name="__zillians_api_math_fmax"; }
	function fmax(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_fmaxf"; }
	function fmaxf(var x:float32, var y:float32):float32;

	@function_mapper { name="__zillians_api_math_sin"; }
	function sin(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_sinf"; }
	function sinf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_sinf_approx"; }
	function sinf_approx(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_sinh"; }
	function sinh(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_sinhf"; }
	function sinhf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_asin"; }
	function asin(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_asinf"; }
	function asinf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_asinh"; }
	function asinh(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_asinhf"; }
	function asinhf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_cos"; }
	function cos(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_cosf"; }
	function cosf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_cosf_approx"; }
	function cosf_approx(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_cosh"; }
	function cosh(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_coshf"; }
	function coshf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_acos"; }
	function acos(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_acosf"; }
	function acosf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_acosh"; }
	function acosh(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_acoshf"; }
	function acoshf(var x:float32):float32;

	@function_mapper { name="llvm.function-mapper.math.cos"; }
	function tan(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_tanf"; }
	function tanf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_tanf_approx"; }
	function tanf_approx(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_tanh"; }
	function tanh(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_tanhf"; }
	function tanhf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_atan"; }
	function atan(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_atanf"; }
	function atanf(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_atan2"; }
	function atan2(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_atan2f"; }
	function atan2f(var x:float32, var y:float32):float32;
	@function_mapper { name="__zillians_api_math_atanh"; }
	function atanh(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_atanhf"; }
	function atanhf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_sqrt"; }
	function sqrt(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_sqrtf"; }
	function sqrtf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_sqrtf_approx"; }
	function sqrtf_approx(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_rsqrt"; }
	function rsqrt(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_rsqrtf"; }
	function rsqrtf(var x:float32):float32;
	@function_mapper { name="__zillians_api_math_rsqrtf_approx"; }
	function rsqrtf_approx(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_exp2"; }
	function exp2(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_exp2f"; }
	function exp2f(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_exp10"; }
	function exp10(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_exp10f"; }
	function exp10f(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_exp"; }
	function exp(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_expf"; }
	function expf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_expm1"; }
	function expm1(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_expm1f"; }
	function expm1f(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_log2"; }
	function log2(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_log2f"; }
	function log2f(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_log10"; }
	function log10(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_log10f"; }
	function log10f(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_log"; }
	function log(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_logf"; }
	function logf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_log1p"; }
	function log1p(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_log1pf"; }
	function log1pf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_logb"; }
	function logb(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_logbf"; }
	function logbf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_ilogb"; }
	function ilogb(var x:float64):int32;
	@function_mapper { name="__zillians_api_math_ilogbf"; }
	function ilogbf(var x:float32):int32;

	@function_mapper { name="__zillians_api_math_scalbn"; }
	function scalbn(var x:float64, var n:int32):float64;
	@function_mapper { name="__zillians_api_math_scalbnf"; }
	function scalbnf(var x:float32, var n:int32):float32;

	@function_mapper { name="__zillians_api_math_scalbln"; }
	function scalbln(var x:float64, var l:int64):float64;
	@function_mapper { name="__zillians_api_math_scalblnf"; }
	function scalblnf(var x:float32, var l:int64):float32;

	@function_mapper { name="__zillians_api_math_pow"; }
	function pow(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_powf"; }
	function powf(var x:float32, var y:float32):float32;

	//function modf
	//function fmodf

	@function_mapper { name="__zillians_api_math_remainder"; }
	function remainder(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_remainderf"; }
	function remainderf(var x:float32, var y:float32):float32;

	//function remquo

	@function_mapper { name="__zillians_api_math_floor"; }
	function floor(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_floorf"; }
	function floorf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_ceil"; }
	function ceil(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_ceilf"; }
	function ceilf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_erf"; }
	function erf(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_erff"; }
	function erff(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_erfc"; }
	function erfc(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_erfcf"; }
	function erfcf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_erfinv"; }
	function erfinv(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_erfinvf"; }
	function erfinvf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_erfcinv"; }
	function erfcinv(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_erfcinvf"; }
	function erfcinvf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_copysign"; }
	function copysign(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_copysignf"; }
	function copysignf(var x:float32, var y:float32):float32;

	@function_mapper { name="__zillians_api_math_nextafter"; }
	function nextafter(var x:float64, var y:float64):float64;
	@function_mapper { name="__zillians_api_math_nextafter"; }
	function nextafterf(var x:float32, var y:float32):float32;

	@function_mapper { name="__zillians_api_math_lgamma"; }
	function lgamma(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_lgammaf"; }
	function lgammaf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_fma"; }
	function fma(var x:float64, var y:float64, var z:float64):float64;
	@function_mapper { name="__zillians_api_math_fmaf"; }
	function fmaf(var x:float32, var y:float32, var z:float32):float32;

	// frexp
	// ldexp

	//@function_mapper { name="__zillians_api_math_rcp"; }
	//function rcp(var x:float64):float64;
	//@function_mapper { name="__zillians_api_math_rcpf"; }
	//function rcpf(var x:float32):float32;
	//@function_mapper { name="__zillians_api_math_rcpf_approx"; }
	//function rcpf_approx(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_round"; }
	function round(var x:float64):float64;
	@function_mapper { name="__zillians_api_math_roundf"; }
	function roundf(var x:float32):float32;

	@function_mapper { name="__zillians_api_math_iround"; }
	function iround(var x:float64):int32;
	@function_mapper { name="__zillians_api_math_iroundf"; }
	function iroundf(var x:float32):int32;

	@function_mapper { name="__zillians_api_math_lround"; }
	function lround(var x:float64):int64;
	@function_mapper { name="__zillians_api_math_lroundf"; }
	function lroundf(var x:float32):int64;

	@function_mapper { name="__zillians_api_math_rand"; }
	function rand():int32;

	@function_mapper { name="__zillians_api_math_randf"; }
	function randf():float32;
}
